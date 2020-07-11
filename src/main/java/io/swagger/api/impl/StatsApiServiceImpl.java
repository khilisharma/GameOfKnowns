package io.swagger.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import io.swagger.api.NotFoundException;
import io.swagger.api.StatsApiService;
import io.swagger.model.Statistics;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
@AllArgsConstructor
@Slf4j
public class StatsApiServiceImpl extends StatsApiService {

  @Inject
  private ObjectMapper mapper;
  @Inject
  private GameDao gameDao;

  @Override
  public Response getStatistics(String gameId, String questionId, SecurityContext securityContext)
      throws NotFoundException {
    try {
      final Map<String, Integer> stats = gameDao.getStatistics(questionId, gameId);
      final Statistics response = new Statistics();
      for (Entry<String, Integer> entry : stats.entrySet()) {
        response.put(entry.getKey(), entry.getValue());
      }
      return Response.ok().entity(
          mapper.writeValueAsString(response)).build();
    } catch (final ResourceNotFoundException ex) {
      log.debug("Resource not found gameId: {}, questionId: {}", gameId, questionId, ex);
      return Response.status(Status.NOT_FOUND).entity("Invalid Request parameters").build();
    } catch (final Exception ex) {
      log.error("Something went wrong!!", ex);
      return Response.serverError().build();
    }
  }
}
