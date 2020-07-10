package io.swagger.api.factories;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SerlizerFactories {

  private static final ObjectMapper mapper = new ObjectMapper();

  public static ObjectMapper getMapper() {
    return mapper;
  }
}
