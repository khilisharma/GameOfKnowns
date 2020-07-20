package io.swagger.api.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.TokenDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.PlayerStatus;
import com.gameofknowns.dao.model.PlayerStatusEnum;
import io.swagger.api.NotFoundException;
import io.swagger.model.TokenDetails;
import io.swagger.model.TokenDetails.StatusEnum;
import java.util.Optional;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TokenApiServiceImplTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String tokenId = "398648732568";
  private static final String gameId = "8634726";
  private static final String playerId = "8634778";
  private static final PlayerStatus STATUS_WAIT = PlayerStatus.builder().status(PlayerStatusEnum.WAIT).build();
  private static final PlayerStatus STATUS_JOINED = PlayerStatus.builder().gameId(Optional.of(gameId)).playerId(Optional.of(playerId)).status(PlayerStatusEnum.JOINED).build();

  @Mock
  TokenDao tokenDao;

  private TokenApiServiceImpl tokenApiService;

  @Before
  public void setUp() throws Exception {
    tokenApiService = new TokenApiServiceImpl(tokenDao, MAPPER);
  }

  @Test
  public void testDescribeTokenWith200Response_WaitStatus()
      throws NotFoundException, JsonProcessingException {

    when(tokenDao.getStatus(tokenId)).thenReturn(STATUS_WAIT);

    final TokenDetails responseToken = new TokenDetails();
    responseToken.setStatus(StatusEnum.WAIT);

    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(responseToken))
        .build();
    final Response actual = tokenApiService.describeToken(tokenId, null);

    assertEquals("assert status", expected.getStatus(), actual.getStatus());
    assertEquals("assert entity", expected.getEntity(), actual.getEntity());
  }

  @Test
  public void testDescribeTokenWith200Response_JoinedStatus()
      throws NotFoundException, JsonProcessingException {
    when(tokenDao.getStatus(tokenId)).thenReturn(STATUS_JOINED);

    final TokenDetails responseToken = new TokenDetails();
    responseToken.setStatus(StatusEnum.JOINED);
    responseToken.setPlayerId(playerId);
    responseToken.setGameId(gameId);

    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(responseToken))
        .build();
    final Response actual = tokenApiService.describeToken(tokenId, null);

    assertEquals("assert status", expected.getStatus(), actual.getStatus());
    assertEquals("assert entity", expected.getEntity(), actual.getEntity());
  }

  @Test
  public void testDescribeTokenWith404Response() throws NotFoundException{
    when(tokenDao.getStatus(anyString())).thenThrow(ResourceNotFoundException.class);

    final Response actual = tokenApiService.describeToken("t2", null);

    assertEquals("assert status ", 404, actual.getStatus());

  }
}