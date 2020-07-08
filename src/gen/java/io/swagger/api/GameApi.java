package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.GameApiService;
import io.swagger.api.factories.GameApiServiceFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.model.Body;
import io.swagger.model.JoinGameJob;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;


@Path("/game")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")public class GameApi  {
   private final GameApiService delegate;

   public GameApi(@Context ServletConfig servletContext) {
      GameApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("GameApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (GameApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = GameApiServiceFactory.getGameApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @Operation(summary = "Join the Game", description = "Lets the player create profile and join the next available game", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Token Id needed to get playerId and gameId", content = @Content(schema = @Schema(implementation = JoinGameJob.class))),
        
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        
        @ApiResponse(responseCode = "500", description = "Internal Server Exception") })
    public Response joinGame(@Parameter(in = ParameterIn.DEFAULT, description = "" ,required=true) Body body

,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.joinGame(body,securityContext);
    }
}
