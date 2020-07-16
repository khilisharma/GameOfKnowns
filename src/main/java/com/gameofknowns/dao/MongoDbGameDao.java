package com.gameofknowns.dao;

import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_FIRST_CHOICE_COUNT;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_GAME_ID;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_GAME_STATUS;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_PLAYERS;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_PLAYER_ID;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_QUESTION_ID;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_ROUND;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_SECOND_CHOICE_COUNT;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_THIRD_CHOICE_COUNT;
import static com.gameofknowns.constants.StringConstants.COLLECTION_GAMES;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.Game;
import com.gameofknowns.dao.model.GameStatusEnum;
import com.gameofknowns.dao.model.Player;
import com.gameofknowns.dao.model.Question;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.bson.Document;


@AllArgsConstructor
@Builder
@Singleton
public class MongoDbGameDao implements GameDao {

  @Inject
  private QuestionsDao questionsDao;

  @Inject
  private MongoDatabase database;

  /**
   * Adds player to game when game not in progress
   *
   * @param player
   * @param gameId
   */
  @Override
  public void addPlayer(Player player, String gameId) {
    final Document newPlayerDocument = new Document();
    newPlayerDocument.append(ATTRIBUTE_PLAYER_ID, player.getPlayerId());
    // since this is a set. It guarantees idempotency and uniqueness
    final Game latestRound = database.getCollection(COLLECTION_GAMES)
        .find(eq(ATTRIBUTE_GAME_ID, gameId), Game.class).sort(eq(ATTRIBUTE_ROUND, -1)).first();
    if (latestRound == null) {
      throw new ResourceNotFoundException("GameId doesn't exist");
    }
    database.getCollection(COLLECTION_GAMES)
        .updateOne(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_ROUND, latestRound.getRound())),
            Updates.push(ATTRIBUTE_PLAYERS, newPlayerDocument));
  }

  /**
   * Advances player to next round. Creates a new round and adds player if no next round exist.
   *
   * @param gameId
   * @param playerId
   */
  @Override
  public void advancePlayer(String gameId, String playerId) {
    // verify if new round exists
    Game newRound = database.getCollection(COLLECTION_GAMES)
        .find(and(eq(ATTRIBUTE_GAME_ID, gameId),
            eq(ATTRIBUTE_GAME_STATUS, GameStatusEnum.WAITING_FOR_PLAYERS.toString())), Game.class)
        .sort(eq(ATTRIBUTE_ROUND, -1)).first();

    // if no create new one
    if (null == newRound) {
      Game currentRound = database.getCollection(COLLECTION_GAMES)
          .find(eq(ATTRIBUTE_GAME_ID, gameId), Game.class).sort(eq(ATTRIBUTE_ROUND, -1)).first();
      currentRound.setRound(Integer.parseInt(currentRound.getRound()) + 1 + "");
      currentRound.setGameStatus(GameStatusEnum.WAITING_FOR_PLAYERS.toString());
      currentRound.setPlayers(Arrays.asList());
      currentRound.setQuestionId("");
      database.getCollection(COLLECTION_GAMES, Game.class).insertOne(currentRound);
    } else {
      final Document newPlayerDocument = new Document();
      newPlayerDocument.append(ATTRIBUTE_PLAYER_ID, playerId);
      database.getCollection(COLLECTION_GAMES)
          .updateOne(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_ROUND, newRound.getRound())),
              Updates.push(ATTRIBUTE_PLAYERS, newPlayerDocument));
    }
    // add player to new round
    addPlayer(new Player(playerId, null), gameId);
  }

  /**
   * Adds a new question to game, if not being played.
   *
   * @param questionId
   * @param gameId
   * @throws IllegalAccessException if tried to update question for game in progress
   */
  @Override
  public void addQuestion(String questionId, String gameId) {
    final Game game = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(and(eq(ATTRIBUTE_GAME_ID, gameId),
            eq(ATTRIBUTE_GAME_STATUS, GameStatusEnum.WAITING_FOR_PLAYERS.toString())))
        .sort(eq(ATTRIBUTE_ROUND, -1)).first();
    if (null == game) {
      throw new IllegalStateException("Game is already in progress!!");
    }
    database.getCollection(COLLECTION_GAMES)
        .updateOne(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_ROUND, game.getRound())),
            Updates.set(ATTRIBUTE_QUESTION_ID, questionId));
  }

  /**
   * Gets the questionId for specific game on the latest round
   *
   * @param gameId
   * @param playerId
   * @return
   */
  @Override
  public Question getQuestion(String gameId, String playerId) {
    final Game currentQuestion = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(and(eq(ATTRIBUTE_GAME_ID, gameId),
            eq(ATTRIBUTE_GAME_STATUS, GameStatusEnum.IN_PROGRESS.toString())))
        .sort(eq(ATTRIBUTE_ROUND, -1)).first();
    if (currentQuestion == null) {
      throw new IllegalStateException("Game has not started");
    }
    final Question question = questionsDao.getQuestion(currentQuestion.getQuestionId());
    return question;
  }

  /**
   * Gets statistics of the game for a specific question
   *
   * @param questionId
   * @param gameId
   * @return
   */
  @Override
  public Map<String, Integer> getStatistics(String questionId, String gameId) {
    final Game currentQuestion = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_QUESTION_ID, questionId)))
        .first();
    Map<String, Integer> result = new HashMap<>();
    if (null == currentQuestion) {
      throw new ResourceNotFoundException("Invalid gameId/ questionId");
    }
    result.put("1", currentQuestion.getFirstChoiceCount());
    result.put("2", currentQuestion.getSecondChoiceCount());
    result.put("3", currentQuestion.getThirdChoiceCount());
    return result;
  }

  /**
   * Creates a new game entry
   *
   * @return
   */
  @Override
  public String createGame() {
    String gameId = "G" + UUID.randomUUID().toString();
    final Game newGame = Game.builder()
        .gameId(gameId)
        .firstChoiceCount(0)
        .secondChoiceCount(0)
        .thirdChoiceCount(0)
        .gameStatus(GameStatusEnum.WAITING_FOR_PLAYERS.toString())
        .players(Collections.emptyList())
        .questionId("")
        .round("1")
        .createdAt(Instant.now().getEpochSecond() + "")
        .build();
    database.getCollection(COLLECTION_GAMES, Game.class).insertOne(newGame);
    return gameId;
  }

  /**
   * TODO: Paginate later
   *
   * @return
   */
  @Override
  public List<Game> getOpenGames() {
    FindIterable<Game> openGames = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(eq(ATTRIBUTE_GAME_STATUS, GameStatusEnum.WAITING_FOR_PLAYERS.toString()));
    List<Game> result = new ArrayList<>();
    for (Game g : openGames) {
      result.add(g);
    }
    return result;
  }

  @Override
  public List<Game> getInProgressGames() {
    FindIterable<Game> openGames = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(eq(ATTRIBUTE_GAME_STATUS, GameStatusEnum.IN_PROGRESS.toString()));
    List<Game> result = new ArrayList<>();
    for (Game g : openGames) {
      result.add(g);
    }
    return result;
  }

  /**
   * Check if player is still in the game.
   *
   * @param gameId
   * @param playerId
   * @return
   */
  @Override
  public boolean isPlayerInTheGame(String gameId, String playerId) {
    final Game game = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(eq(ATTRIBUTE_GAME_ID, gameId)).sort(eq(ATTRIBUTE_ROUND, -1)).first();
    final List<Map<String, String>> players = game.getPlayers();
    for (Map<String, String> player : players) {
      if (player.get(ATTRIBUTE_PLAYER_ID).equals(playerId)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void startGame(String gameId, String roundId) {
    database.getCollection(COLLECTION_GAMES).updateOne(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_ROUND, roundId)),
        Updates.set(ATTRIBUTE_GAME_STATUS, GameStatusEnum.IN_PROGRESS.toString()));
  }

  @Override
  public void closeGame(String gameId, String roundId) {
    database.getCollection(COLLECTION_GAMES).updateOne(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_ROUND, roundId)),
        Updates.set(ATTRIBUTE_GAME_STATUS, GameStatusEnum.CLOSED.toString()));
  }

  @Override
  public boolean isQuestionAsked(String gameId, String questionId) {
    final Game game = database.getCollection(COLLECTION_GAMES, Game.class)
        .find(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_QUESTION_ID, questionId))).first();
    return game == null;
  }

  /**
   * Increments the count of the choice for question in game
   *
   * @param questionId
   * @param choiceId
   * @param gameId
   */
  @Override
  public void countAnswerChoice(String questionId, String choiceId, String gameId) {
    String choiceAttribute = "";
    switch (choiceId) {
      case "1": {
        choiceAttribute = ATTRIBUTE_FIRST_CHOICE_COUNT;
        break;
      }
      case "2": {
        choiceAttribute = ATTRIBUTE_SECOND_CHOICE_COUNT;
        break;
      }
      case "3": {
        choiceAttribute = ATTRIBUTE_THIRD_CHOICE_COUNT;
        break;
      }
      default:
        break;
    }
    database.getCollection(COLLECTION_GAMES)
        .updateOne(and(eq(ATTRIBUTE_GAME_ID, gameId), eq(ATTRIBUTE_QUESTION_ID, questionId)),
            Updates.inc(choiceAttribute, 1));
  }

  @Override
  public Integer numberOfPlayers(String gameId){
    final Game game = database.getCollection(COLLECTION_GAMES, Game.class)
      .find(and(eq(ATTRIBUTE_GAME_ID, gameId))).sort(eq(ATTRIBUTE_ROUND, -1)).first();

    return game.getPlayers().size();

  }
}
