package io.swagger.api.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.MockTokenDao;
import io.swagger.api.TokenApiService;
import io.swagger.api.impl.TokenApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class TokenApiServiceFactory {

  private final static TokenApiService service = new TokenApiServiceImpl(new MockTokenDao(),
      new ObjectMapper());

  public static TokenApiService getTokenApi() {
    return service;
  }
}
