package io.swagger.api.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import io.swagger.api.NotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WinnerApiServiceImplTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String IS_WINNER = "isWinner";
  private static final String gameId = "763875";
  private static final String playerId = "254564";
  private static final Map<String, Boolean> responseMap = new HashMap<>();

  @Mock
  GameDao gameDao;

  WinnerApiServiceImpl winnerApiService;

  @Before
  public void setUp() throws Exception {
    winnerApiService = new WinnerApiServiceImpl(MAPPER, gameDao);

  }

  @Test
  public void testIsWinnerWith200Response() throws NotFoundException, JsonProcessingException {
    when(gameDao.isPlayerInTheGame(gameId, playerId)).thenReturn(true);
    when(gameDao.numberOfPlayers(gameId)).thenReturn(1);
    responseMap.put(IS_WINNER, true);

    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(responseMap))
        .build();
    final Response actual =  winnerApiService.isWinner(gameId, playerId, null);

    assertEquals("assert status", expected.getStatus(), actual.getStatus());
    assertEquals("assert entity", expected.getEntity(), actual.getEntity());

  }

  @Test
  public void testIsWinnerWith404Response() throws NotFoundException{
    when(gameDao.isPlayerInTheGame(anyString(), anyString())).thenThrow(ResourceNotFoundException.class);

    final Response actual = winnerApiService.isWinner("p2", "g4", null);

    assertEquals("assert status ", 404, actual.getStatus());
  }

}