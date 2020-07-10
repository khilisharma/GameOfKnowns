package io.swagger.api.factories;

import io.swagger.api.AnswersApiService;
import io.swagger.api.impl.AnswersApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class AnswersApiServiceFactory {

  private final static AnswersApiService service = new AnswersApiServiceImpl(
      MongoDbDriverFactories.getQuestionsDao(), MongoDbDriverFactories.getGameDao(),
      SerlizerFactories
          .getMapper());

  public static AnswersApiService getAnswersApi() {
    return service;
  }
}
