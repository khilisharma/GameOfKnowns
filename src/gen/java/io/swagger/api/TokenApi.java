package io.swagger.api;

import io.swagger.api.factories.TokenApiServiceFactory;
import io.swagger.model.TokenDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/token")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class TokenApi {

  private final TokenApiService delegate;

  public TokenApi(@Context ServletConfig servletContext) {
    TokenApiService delegate = null;

    if (servletContext != null) {
      String implClass = servletContext.getInitParameter("TokenApi.implementation");
      if (implClass != null && !"".equals(implClass.trim())) {
        try {
          delegate = (TokenApiService) Class.forName(implClass).newInstance();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }

    if (delegate == null) {
      delegate = TokenApiServiceFactory.getTokenApi();
    }

    this.delegate = delegate;
  }

  @GET

  @Produces({"application/json"})
  @Operation(summary = "Get the status, and if status is JOINED, get the playerId and gameId", description = "Lets the player retrieve status, playerId and gameId", tags = {})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "TokenDetails include status, playerId and gameID", content = @Content(schema = @Schema(implementation = TokenDetails.class))),

      @ApiResponse(responseCode = "404", description = "Resource Not found"),

      @ApiResponse(responseCode = "500", description = "Internal Server Exception")})
  public Response describeToken(
      @Parameter(in = ParameterIn.QUERY, description = "Unique identifier returned to a player needed to retrieve gameId and playerId", required = true) @QueryParam("tokenId") String tokenId
      , @Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.describeToken(tokenId, securityContext);
  }
}
