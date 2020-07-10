package io.swagger.api.factories;

import io.swagger.api.QuestionsApiService;
import io.swagger.api.impl.QuestionsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class QuestionsApiServiceFactory {

  private final static QuestionsApiService service = new QuestionsApiServiceImpl(
      SerlizerFactories.getMapper(),
      MongoDbDriverFactories.getGameDao());

  public static QuestionsApiService getQuestionsApi() {
    return service;
  }
}
