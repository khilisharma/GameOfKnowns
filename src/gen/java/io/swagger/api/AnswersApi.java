package io.swagger.api;

import io.swagger.api.factories.AnswersApiServiceFactory;
import io.swagger.model.SubmitAnswerRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.servlet.ServletConfig;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/answers")

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class AnswersApi {

  private final AnswersApiService delegate;

  public AnswersApi(@Context ServletConfig servletContext) {
    AnswersApiService delegate = null;

    if (servletContext != null) {
      String implClass = servletContext.getInitParameter("AnswersApi.implementation");
      if (implClass != null && !"".equals(implClass.trim())) {
        try {
          delegate = (AnswersApiService) Class.forName(implClass).newInstance();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }

    if (delegate == null) {
      delegate = AnswersApiServiceFactory.getAnswersApi();
    }

    this.delegate = delegate;
  }

  @POST

  @Consumes({"application/json"})
  @Produces({"application/json"})
  @Operation(summary = "Submit answer for a question", description = "Lets the player submit answer for a question", tags = {})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Choice can be RIGHT or WRONG", content = @Content(schema = @Schema(implementation = String.class))),

      @ApiResponse(responseCode = "400", description = "Bad input parameter"),

      @ApiResponse(responseCode = "500", description = "Internal Server error")})
  public Response submitAnswer(
      @Parameter(in = ParameterIn.DEFAULT, description = "", required = true) SubmitAnswerRequest body

      , @Context SecurityContext securityContext)
      throws NotFoundException {
    return delegate.submitAnswer(body, securityContext);
  }
}
