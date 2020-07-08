package com.gameofknows.dao;

import com.gameofknows.dao.model.Game;
import com.gameofknows.dao.model.Player;
import io.swagger.model.Choice;
import java.util.List;
import java.util.Map;

public interface GameDao {
  void addPlayers(List<Player> players, String gameId);
  void addQuestion(String questionId, String gameId);
  Map<Choice, Integer> getStatistics(String questionId, String gameId, String playerId);
  String createGame();
  List<Game> getOpenGames();
}
