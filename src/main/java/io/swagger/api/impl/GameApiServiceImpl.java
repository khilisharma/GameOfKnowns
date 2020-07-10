package io.swagger.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.TokenDao;
import io.swagger.api.GameApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.Body;
import io.swagger.model.JoinGameJob;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
@AllArgsConstructor
@Slf4j
public class GameApiServiceImpl extends GameApiService {

  @Inject
  private TokenDao tokenDao;
  @Inject
  private ObjectMapper mapper;

  @Override
  public Response joinGame(Body body, SecurityContext securityContext) throws NotFoundException {
    final String name = body.getName();
    if (StringUtils.isBlank(name)) {
      return Response.status(Status.BAD_REQUEST).build();
    }
    try {
      final UUID playerUuid = UUID.randomUUID();
      final String tokenId = tokenDao.addPlayer(name, playerUuid.randomUUID().toString());
      // TODO: think of retry mechanism when the game is filled while processing the request
      final JoinGameJob reponse = new JoinGameJob();
      reponse.setTokenId(tokenId);
      return Response.ok().entity(mapper.writeValueAsString(reponse)).build();
    } catch (final Exception ex) {
      log.error("Something went wrong!!", ex);
      return Response.serverError().build();
    }

  }
}
