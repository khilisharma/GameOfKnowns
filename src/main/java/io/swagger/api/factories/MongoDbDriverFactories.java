package io.swagger.api.factories;

import com.gameofknowns.adapter.MongoDBAdapter;
import com.gameofknowns.dao.MongoDbGameDao;
import com.gameofknowns.dao.MongoDbQuestionsDao;
import com.gameofknowns.dao.MongoDbTokenDao;

public class MongoDbDriverFactories {

  private static final MongoDBAdapter mongoDBAdapter = MongoDBAdapter.builder()
      .databaseName("TriviaGameDatabase")
      .ipAddress("172.31.9.162")
      .portNumber(27017)
      .build();

  private static final MongoDbTokenDao tokenDao = MongoDbTokenDao.builder()
      .database(mongoDBAdapter.getDatabase()).build();
  private static final MongoDbQuestionsDao questionsDao = MongoDbQuestionsDao.builder()
      .database(mongoDBAdapter.getDatabase()).build();
  private static final MongoDbGameDao gameDao = MongoDbGameDao.builder()
      .database(mongoDBAdapter.getDatabase()).questionsDao(questionsDao).build();

  public static MongoDBAdapter getMongoDBAdapter() {
    return mongoDBAdapter;
  }

  public static MongoDbTokenDao getTokenDao() {
    return tokenDao;
  }

  public static MongoDbGameDao getGameDao() {
    return gameDao;
  }

  public static MongoDbQuestionsDao getQuestionsDao() {
    return questionsDao;
  }
}
