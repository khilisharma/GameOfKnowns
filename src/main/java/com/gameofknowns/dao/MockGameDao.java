package com.gameofknowns.dao;

import com.gameofknowns.dao.model.Game;
import com.gameofknowns.dao.model.Player;
import com.gameofknowns.dao.model.Question;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;

public class MockGameDao implements GameDao {

  @Override
  public void addPlayer(Player player, String gameId) {

  }

  @Override
  public void advancePlayer(String gameId, String playerId) {

  }

  @Override
  public void addQuestion(String questionId, String gameId) {

  }

  @SneakyThrows
  @Override
  public Question getQuestion(String gameId, String playerId) {
    if (playerId.equals("2")) {
      throw new IllegalAccessException("Player Not Allowed");
    }
    return new Question(new HashMap<>(), "123123", "asdasd", new HashMap<>());
  }

  @Override
  public Map<String, Integer> getStatistics(String questionId, String gameId) {
    return new HashMap<>();
  }

  @Override
  public String createGame() {
    return "123134123";
  }

  @Override
  public List<Game> getOpenGames() {
    return Arrays.asList();
  }

  @Override
  public List<Game> getInProgressGames() {
    return null;
  }

  @Override
  public void submitAnswer(String questionId, String choiceId, String gameId) {

  }

  @Override
  public boolean isPlayerInTheGame(String gameId, String playerId) {
    return false;
  }

  @Override
  public void startGame(String gameId) {

  }

  @Override
  public void closeGame(String gameId) {

  }
}
