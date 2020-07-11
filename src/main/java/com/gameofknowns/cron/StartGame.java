package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import com.gameofknowns.dao.model.Game;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Inject;
import lombok.AllArgsConstructor;

/**
 * Checks for records in the games collection with status as "WAITING_FOR_PLAYERS" and if it has
 * been 30 seconds since game creation, updates the status to "IN PROGRESS" which means that the
 * game starts now
 */
@AllArgsConstructor
public class StartGame {

  @Inject
  private GameDao gameDao;
  @Inject
  private QuestionsDao questionDao;


  public void init() {
    Instant now = Instant.now();
    List<Game> openGames = gameDao.getOpenGames();

    for (Game game : openGames) {
      // If it has been 30 seconds since the game was created, start the game
      if (now.getEpochSecond() >= Long.parseLong(game.getCreatedAt()) + 30) {
        String gameId = game.getGameId();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
        String questionId = "Q" + randomNum;
        while (!gameDao.isQuestionAsked(gameId, questionId)) {
          randomNum = ThreadLocalRandom.current().nextInt(1, 10);
          questionId = "Q" + randomNum;
        }
        gameDao.addQuestion(questionId, gameId);
        gameDao.startGame(gameId, game.getRound());
      }
    }
  }
}
