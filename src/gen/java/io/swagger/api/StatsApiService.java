package io.swagger.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public abstract class StatsApiService {

  public abstract Response getStatistics(String gameId, String questionId,
      SecurityContext securityContext) throws NotFoundException;
}
