package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.StatsApiService;
import io.swagger.api.factories.StatsApiServiceFactory;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import io.swagger.model.Statistics;

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


@Path("/stats")


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")public class StatsApi  {
   private final StatsApiService delegate;

   public StatsApi(@Context ServletConfig servletContext) {
      StatsApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("StatsApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (StatsApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = StatsApiServiceFactory.getStatsApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    
    @Produces({ "application/json" })
    @Operation(summary = "Get the statistics for a question/round in a game.", description = "Displays the statistics for a question/round in a game.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Statistics object which shows how many players chose each answer", content = @Content(schema = @Schema(implementation = Statistics.class))),
        
        @ApiResponse(responseCode = "400", description = "Bad input parameter"),
        
        @ApiResponse(responseCode = "401", description = "Not authorized to access"),
        
        @ApiResponse(responseCode = "404", description = "Resource Not Found") })
    public Response getStatistics(@Parameter(in = ParameterIn.QUERY, description = "Unique identifier of the game to which the player gets joined to") @QueryParam("gameId") String gameId
,@Parameter(in = ParameterIn.QUERY, description = "Unique identifier for a question") @QueryParam("questionId") String questionId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getStatistics(gameId,questionId,securityContext);
    }
}
