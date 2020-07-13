package com.gameofknowns.cron;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import com.gameofknowns.dao.TokenDao;
import io.swagger.api.factories.MongoDbDriverFactories;
import java.io.IOException;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class GameOfKnownsOrchestrator {

  @Option(name = "-str")
  private String str = "CreateGames";

  public static void main(String[] args) throws IOException {
    new GameOfKnownsOrchestrator().doMain(args);
  }

  public void doMain(String[] args) throws IOException {
    GameDao gameDao = MongoDbDriverFactories.getGameDao();
    TokenDao tokenDao = MongoDbDriverFactories.getTokenDao();
    QuestionsDao questionsDao = MongoDbDriverFactories.getQuestionsDao();
    CmdLineParser parser = new CmdLineParser(this);

    StartGame startgame = new StartGame(gameDao, questionsDao);
    CloseRound closeRound = new CloseRound(gameDao);
    CreateGames createGames = new CreateGames(gameDao);
    PlayerAssigner playerAssigner = new PlayerAssigner(gameDao, tokenDao);

    try {
      parser.parseArgument(args);
      if (args == null) {
        throw new CmdLineException(parser, "No argument is given");
      }

      if (args.equals("CloseRound")) {
        closeRound.init();
      } else if (args.equals("CreateGames")) {
        createGames.init();
      } else if (args.equals("PlayerAssigner")) {
        playerAssigner.init();
      } else if (args.equals("StartGame")) {
        startgame.init();
      } else {
        System.exit(1);
      }
      System.exit(0);
    } catch (CmdLineException e) {
      System.err.println(e.getMessage());
      System.err.println("java SampleMain [options...] arguments...");
      // print the list of available options
      parser.printUsage(System.err);
      System.err.println();
      return;
    }

  }
}