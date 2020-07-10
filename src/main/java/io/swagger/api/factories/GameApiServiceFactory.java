package io.swagger.api.factories;

import io.swagger.api.GameApiService;
import io.swagger.api.impl.GameApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class GameApiServiceFactory {

  private final static GameApiService service = new GameApiServiceImpl(
      MongoDbDriverFactories.getTokenDao(),
      SerlizerFactories.getMapper());

  public static GameApiService getGameApi() {
    return service;
  }
}
