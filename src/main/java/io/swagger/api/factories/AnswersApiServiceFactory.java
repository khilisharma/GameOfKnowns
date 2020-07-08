package io.swagger.api.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.MockGameDao;
import com.gameofknowns.dao.MockQuestionsDao;
import io.swagger.api.AnswersApiService;
import io.swagger.api.impl.AnswersApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
public class AnswersApiServiceFactory {

  private final static AnswersApiService service = new AnswersApiServiceImpl(new MockQuestionsDao(),
      new MockGameDao(), new ObjectMapper());

  public static AnswersApiService getAnswersApi() {
    return service;
  }
}
