package io.swagger.api.impl;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.TokenDao;
import io.swagger.api.NotFoundException;
import io.swagger.model.Body;
import io.swagger.model.JoinGameJob;
import java.util.UUID;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameApiServiceImplTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String name = "Jonathon";
  private static final String STATUS = "status";
  private static final String tokenId = "7464783";
  private Body body = new Body();
  @Mock
  TokenDao tokenDao;
  private GameApiServiceImpl gameApiService;
  @Before
  public void setUp() throws Exception {
    gameApiService = new GameApiServiceImpl(tokenDao, MAPPER);
  }

  @Test
  public void testJoinGame_200JoinedGame() throws NotFoundException, JsonProcessingException {
    body.setName(name);

    when(tokenDao.addPlayer(anyString(), eq(body.getName()))).thenReturn(tokenId);
    final JoinGameJob response = new JoinGameJob();
    response.setTokenId(tokenId);

    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(response)).build();
    final Response actual = gameApiService.joinGame(body, null);

    assertEquals("assert entity", expected.getEntity(), actual.getEntity());
    assertEquals("assert status", expected.getStatus(), actual.getStatus());
  }

  @Test
  public void testJoinGame_400() throws NotFoundException{
    body.setName("    ");

    final Response response = gameApiService.joinGame(body, null);
    final Response expected = Response.status(Status.BAD_REQUEST).build();

    assertEquals("assert status" , expected.getStatus(), response.getStatus());
    assertEquals("assert entity", expected.getEntity(), response.getEntity());
  }
}