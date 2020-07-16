package io.swagger.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import io.swagger.api.NotFoundException;
import io.swagger.api.WinnerApiService;
import io.swagger.model.SubmitAnswerResponse;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-16T04:40:34.487Z[GMT]")
@AllArgsConstructor
@Slf4j
public class WinnerApiServiceImpl extends WinnerApiService {
  private static final String IS_WINNER = "isWinner";

  @Inject
  private ObjectMapper mapper;
  @Inject
  private GameDao gameDao;

  @Override
  public Response isWinner(String gameId, String playerId, SecurityContext securityContext)
      throws NotFoundException {
    try{
        final boolean isInTheGame= gameDao.isPlayerInTheGame(gameId, playerId);
        Map<String, Boolean> responseMap = new HashMap<>();
        final int numOfPlayers = gameDao.numberOfPlayers(gameId);
        responseMap.put(IS_WINNER, isInTheGame && numOfPlayers == 1);
        return Response.ok().entity(mapper.writeValueAsString(responseMap)).build();
    } catch (final ResourceNotFoundException ex) {
      log.debug("Resouce not found, gameId: {}, playerId: {}",
          gameId, playerId, ex);
      return Response.status(Status.NOT_FOUND).entity("Invalid request parameters!").build();
    } catch (final Exception ex) {
      log.error("Something went wrong!!", ex);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
