package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import javax.inject.Inject;

public class AddQuestions {

  @Inject
  QuestionsDao questionsDao;

  @Inject
  GameDao gameDao;

  public void init(){
    gameDao.getOpenGames();

  }

}
