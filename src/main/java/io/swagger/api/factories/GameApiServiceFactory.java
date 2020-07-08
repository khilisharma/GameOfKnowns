package io.swagger.api.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.MockTokenDao;
import io.swagger.api.GameApiService;
import io.swagger.api.impl.GameApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class GameApiServiceFactory {

  private final static GameApiService service = new GameApiServiceImpl(new MockTokenDao(),
      new ObjectMapper());

  public static GameApiService getGameApi() {
    return service;
  }
}
