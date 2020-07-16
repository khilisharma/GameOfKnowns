package com.gameofknowns.dao;

import com.gameofknowns.dao.model.Game;
import com.gameofknowns.dao.model.Player;
import com.gameofknowns.dao.model.Question;
import java.util.List;
import java.util.Map;

public interface GameDao {

  void addPlayer(Player player, String gameId);

  void advancePlayer(String gameId, String playerId);

  void addQuestion(String questionId, String gameId);

  Question getQuestion(String gameId, String playerId);

  Map<String, Integer> getStatistics(String questionId, String gameId);

  String createGame();

  List<Game> getOpenGames();

  List<Game> getInProgressGames();

  void countAnswerChoice(String questionId, String choiceId, String gameId);

  boolean isPlayerInTheGame(String gameId, String playerId);

  void startGame(String gameId, String roundId);

  void closeGame(String gameId, String roundId);

  boolean isQuestionAsked(String gameId, String questionId);

  Integer numberOfPlayers(String gameId);
}
