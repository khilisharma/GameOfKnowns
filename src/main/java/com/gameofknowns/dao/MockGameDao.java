package com.gameofknowns.dao;

import com.gameofknowns.dao.model.Choice;
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
      throw new IllegalAccessException("biathc");
    }
    return new Question(new HashMap<String, Choice>(), "123123", "asdasd", null);
  }

  @Override
  public Map<Choice, Integer> getStatistics(String questionId, String gameId) {
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
}
