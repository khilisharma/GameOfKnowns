package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import com.gameofknowns.dao.TokenDao;
import io.swagger.api.factories.MongoDbDriverFactories;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

@Slf4j
public class GameOfKnownsOrchestrator {

  @Option(name = "-n", usage = "Script name to run")
  private String str;

  public static void main(String[] args) throws IOException {
    new GameOfKnownsOrchestrator().doMain(args);
  }

  public void doMain(String[] args) throws IOException {

    CmdLineParser parser = new CmdLineParser(this);

    try {
      parser.parseArgument(args);

      if (StringUtils.isBlank(str)) {
        throw new CmdLineException("No args passed");
      }
      GameDao gameDao = MongoDbDriverFactories.getGameDao();
      TokenDao tokenDao = MongoDbDriverFactories.getTokenDao();
      QuestionsDao questionsDao = MongoDbDriverFactories.getQuestionsDao();


      StartGame startgame = new StartGame(gameDao, questionsDao);
      CloseRound closeRound = new CloseRound(gameDao);
      CreateGames createGames = new CreateGames(gameDao);
      PlayerAssigner playerAssigner = new PlayerAssigner(gameDao, tokenDao);

      if (str.equals("CloseRound")) {
        closeRound.init();
      } else if (str.equals("CreateGames")) {
        createGames.init();
      } else if (str.equals("PlayerAssigner")) {
        playerAssigner.init();
      } else if (str.equals("StartGame")) {
        startgame.init();
      } else {
        log.debug("Invalid option: {}", str);
        System.exit(1);
      }
      System.exit(0);
    } catch (CmdLineException e) {
      log.error("Error: {}", e);
      System.exit(1);
    }
  }
}