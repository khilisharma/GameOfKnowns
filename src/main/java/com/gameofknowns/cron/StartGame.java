package com.gameofknowns.cron;

import static org.apache.commons.lang3.time.DateUtils.addSeconds;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.model.Game;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class StartGame {

  @Inject
  private GameDao gameDao;


  public void init() {
    Instant now = Instant.now();
    List<Game> openGames = gameDao.getOpenGames();

    for(Game game: openGames) {
      // If it has been 30 seconds since the game was created, start the game
      if(now.getEpochSecond()>=Long.parseLong(game.getCreatedAt())+30)  {
        gameDao.startGame(game.getGameId());
      }
    }

  }
}
