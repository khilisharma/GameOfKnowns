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
      .asList("66953637137326",
          "74333282250502",
          "92999544118897",
          "64814496346681",
          "56265644253192",
          "65743446187993",
          "61732591225536",
          "4163382694592",
          "83584452413704",
          "78486992712205",
          "3304652272788",
          "6805727673556",
          "38354785203000",
          "42331990134803",
          "82008103801067",
          "93452780964471",
          "87088171334116",
          "44034169342915",
          "67321680346334",
          "63913387541469",
          "76410642585193",
          "5821281000484",
          "57577800371946",
          "41668844921903",
          "56654540593608",
          "53237772869462",
          "44586571058104",
          "85712194683614",
          "89240202677786",
          "48764215965970",
          "42132342683880",
          "57196010218316",
          "39225894847651",
          "53514708406533",
          "96848060812758",
          "81225374057615",
          "46771409964169",
          "85014610576984",
          "52487811256701",
          "460143072359",
          "63285535523685",
          "13498941882677",
          "74692395196687",
          "28935557720013",
          "49181128073465",
          "475233733741",
          "87408245434080",
          "62021512933835",
          "86964634308422",
          "27378244454277");


  public void init() {
    Instant now = Instant.now();
    List<Game> openGames = gameDao.getOpenGames();

    for (Game game : openGames) {
      // If it has been 30 seconds since the game was created, start the game
      if (now.getEpochSecond() >= Long.parseLong(game.getCreatedAt()) + 2) {
        String gameId = game.getGameId();
        int randomNum = ThreadLocalRandom.current().nextInt(0, questionIds.size());
        String questionId = questionIds.get(randomNum);
        while (!gameDao.isQuestionAsked(gameId, questionId)) {
          randomNum = ThreadLocalRandom.current().nextInt(0, questionIds.size());
          questionId = questionIds.get(randomNum);
        }
        gameDao.addQuestion(questionId, gameId);
        gameDao.startGame(gameId, game.getRound());
      }
    }
  }
}
