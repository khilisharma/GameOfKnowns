package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.WinnerApiService;
import io.swagger.api.factories.WinnerApiServiceFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.model.Winner;

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


@Path("/winner")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-16T04:40:34.487Z[GMT]")public class WinnerApi  {
   private final WinnerApiService delegate;

   public WinnerApi(@Context ServletConfig servletContext) {
      WinnerApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("WinnerApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (WinnerApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = WinnerApiServiceFactory.getWinnerApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    
    @Produces({ "application/json" })
    @Operation(summary = "Checks whether the player is the game of the winner or not.", description = "Displays the winner.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Winner object returns whether the player is winner or not", content = @Content(schema = @Schema(implementation = Winner.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad input parameter"),
        
        @ApiResponse(responseCode = "401", description = "Not authorized to access"),
        
        @ApiResponse(responseCode = "404", description = "Resource Not Found") })
    public Response isWinner(@Parameter(in = ParameterIn.QUERY, description = "Unique identifier of the game which the player was playing") @QueryParam("gameId") String gameId
,@Parameter(in = ParameterIn.QUERY, description = "Unique identifier of the player") @QueryParam("playerId") String playerId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.isWinner(gameId,playerId,securityContext);
    }
}
