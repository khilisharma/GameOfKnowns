package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import com.gameofknowns.dao.model.Game;
import java.time.Instant;
import java.util.Arrays;
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

  private static List<String> questionIds = Arrays
      .asList("32562217468692", "759813378857",   "76151503122063",
      "96927347748647", "7754826454472",  "51065341738242",
      "27354807651225", "19691569260072", "24015480917162",
      "39909384785154", "5023179270174",  "33512204022297",
      "94072158746927", "76194477513999", "4393263420531",
      "15455777260779", "19155591138995", "13999467449583",
      "65147267118754", "35416545743880", "41294871621571",
      "48359705085814", "56202760327987", "62404082742293",
      "24886544936833", "7777342820946",  "38179810094877",
      "9983974484462",  "94291843349402", "59504764735419",
      "31830296578546", "91940512145762", "89661029649318",
      "62906467772394", "86872461192272", "99848396284519",
      "67693085475202", "61354519917252", "84651602544980",
      "72341284772062", "78037690028825", "74135722005895",
      "10067232796864", "80281516761588", "64908874846048",
      "50148789069753", "1163169704664",  "28628123392343",
      "26354765472906", "49907480931389");


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
