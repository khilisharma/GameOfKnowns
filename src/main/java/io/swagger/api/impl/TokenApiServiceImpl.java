package io.swagger.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.TokenDao;
import com.gameofknowns.dao.model.PlayerStatus;
import com.gameofknowns.dao.model.PlayerStatusEnum;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.TokenApiService;
import io.swagger.model.TokenDetails;
import io.swagger.model.TokenDetails.StatusEnum;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
@AllArgsConstructor
public class TokenApiServiceImpl extends TokenApiService {

  @Inject
  private TokenDao tokenDao;
  @Inject
  private ObjectMapper mapper;

  @Override
  public Response describeToken(@NotNull String tokenId, SecurityContext securityContext)
      throws NotFoundException {
    try {
      final PlayerStatus status = tokenDao.getStatus(tokenId);
      final TokenDetails responseToken = new TokenDetails();
      if (PlayerStatusEnum.WAIT.equals(status.getStatus())) {
        responseToken.setStatus(StatusEnum.WAIT);
        return Response.ok()
            .entity(mapper.writeValueAsString(responseToken))
            .build();
      }
      responseToken.setStatus(StatusEnum.JOINED);
      responseToken.setGameId(status.getGameId().orElseThrow(Exception::new));
      responseToken.setPlayerId(status.getPlayerId().orElseThrow(Exception::new));
      return Response.ok()
          .entity(new ApiResponseMessage(ApiResponseMessage.OK,
              mapper.writeValueAsString(responseToken))).build();
    } catch (final Exception ex) {
      return Response.serverError().build();
    }
  }
}
