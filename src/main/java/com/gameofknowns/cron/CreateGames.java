package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;
import javax.inject.Inject;
import lombok.AllArgsConstructor;

/**
 * Creates a record in the games collection with a randomly generated unique number as game ID , round as 1 and status as "WAITING_FOR_PLAYERS"
 */
@AllArgsConstructor
public class CreateGames {

  @Inject
  GameDao gameDao;

  //creates a game entry every minute
  public void init() {
    gameDao.createGame();
  }

}
