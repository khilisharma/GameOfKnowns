package io.swagger.api;

import io.swagger.api.factories.QuestionsApiServiceFactory;
import io.swagger.model.Question;
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


@Path("/questions")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class QuestionsApi {

  private final QuestionsApiService delegate;

  public QuestionsApi(@Context ServletConfig servletContext) {
    QuestionsApiService delegate = null;

    if (servletContext != null) {
      String implClass = servletContext.getInitParameter("QuestionsApi.implementation");
      if (implClass != null && !"".equals(implClass.trim())) {
        try {
          delegate = (QuestionsApiService) Class.forName(implClass).newInstance();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }

    if (delegate == null) {
      delegate = QuestionsApiServiceFactory.getQuestionsApi();
    }

    this.delegate = delegate;
  }

  @GET

  @Produces({"application/json"})
  @Operation(summary = "Get the question along with the multiple choices.", description = "Lets the player get the question in an ongoing game", tags = {})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "a question object", content = @Content(schema = @Schema(implementation = Question.class))),

      @ApiResponse(responseCode = "400", description = "Bad input parameter"),

      @ApiResponse(responseCode = "401", description = "Illegal access exception"),

      @ApiResponse(responseCode = "404", description = "Resource Not found"),

      @ApiResponse(responseCode = "500", description = "Internal server error")})
  public Response getQuestion(
      @Parameter(in = ParameterIn.QUERY, description = "UUID assigned to the player.", required = true) @QueryParam("playerId") String playerId
      ,
      @Parameter(in = ParameterIn.QUERY, description = "Unique identifier of the game to which the player gets joined to", required = true) @QueryParam("gameId") String gameId
      , @Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.getQuestion(playerId, gameId, securityContext);
  }
}
