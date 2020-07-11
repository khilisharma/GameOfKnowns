package com.gameofknowns.cron;

import com.gameofknowns.dao.MongoDbGameDao;
import javax.inject.Inject;

public class CreateGames {

  @Inject
  MongoDbGameDao gameDao;

  //creates a game entry every minute
  public void init() {
    gameDao.createGame();
  }

}
