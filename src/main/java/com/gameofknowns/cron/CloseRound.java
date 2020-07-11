package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;

import com.gameofknowns.dao.model.Game;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;

public class CloseRound {

  @Inject
  private GameDao gameDao;


  //advances round of all games which are IN_PROGRESS every 10 seconds
  public void init() {
    List<Game> inProgressGames = gameDao.getInProgressGames();
    Instant now = Instant.now();
    for(Game g: inProgressGames) {
      if (Long.parseLong(g.getCreatedAt()) >= now.getEpochSecond() + 10) {
        gameDao.closeGame(g.getGameId());
      }
    }
  }

}
