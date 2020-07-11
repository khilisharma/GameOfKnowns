package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;

import com.gameofknowns.dao.model.Game;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import lombok.AllArgsConstructor;

/**
 * Fetches the records from the games collection with status as "IN_PROGRESS" and if it has been 15 seconds since the record creation, updates the status to CLOSED which means the game is CLOSED now
 */
@AllArgsConstructor
public class CloseRound {

  @Inject
  private GameDao gameDao;

  public void init() {
    List<Game> inProgressGames = gameDao.getInProgressGames();
    Instant now = Instant.now();
    for(Game g: inProgressGames) {
      if (Long.parseLong(g.getCreatedAt()) + 15 <= now.getEpochSecond()) {
        gameDao.closeGame(g.getGameId(), g.getRound());
      }
    }
  }

}
