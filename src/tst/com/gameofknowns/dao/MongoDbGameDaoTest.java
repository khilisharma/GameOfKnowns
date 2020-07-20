package com.gameofknowns.dao;

import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_GAME_ID;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_PLAYER_ID;
import static com.gameofknowns.constants.StringConstants.COLLECTION_GAMES;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.Game;
import com.gameofknowns.dao.model.Player;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MongoDbGameDaoTest {

  private static final String gameId = "863746";
  private static final String playerName = "testPlayer";
  private static final String playerId = "876487";
  private static final String round = "2";
  private static final String questionId = "92999544118897";
  private static final String gameStatus = "IN_PROGRESS";
  private static final String createdAt = "1594882889";
  private static final List<Map<String, String>> players = new ArrayList<>();
  private static final Player PLAYER = new Player(playerId, playerName);
  private static final Game GAME = new Game(gameId, round, questionId, gameStatus, players, 27, 152,
      430, createdAt);

  @Mock
  private QuestionsDao questionsDao;
  @Mock
  private GameDao gameDao;
  @Mock
  private MongoDatabase mongoDatabase;
  @Mock
  private MongoCollection<Document> mockDocuments;
  @Mock
  private FindIterable<Game> games;

  private MongoDbGameDao mongoDbGameDao;
  private Document newPlayerDocument;

  @Before
  public void setUp() throws Exception {
    newPlayerDocument = new Document();
    mongoDbGameDao = new MongoDbGameDao(questionsDao, mongoDatabase);
  }

  @Test
  public void addPlayerToGameSuccessResponse() {

    newPlayerDocument.append(ATTRIBUTE_PLAYER_ID, playerId);
    when(mongoDatabase.getCollection(COLLECTION_GAMES)).thenReturn(mockDocuments);
    when(mockDocuments
        .find(com.mongodb.client.model.Filters.eq(ATTRIBUTE_GAME_ID, gameId), Game.class))
        .thenReturn(games);
    when(games.sort(any())).thenReturn(games);
    when(games.first()).thenReturn(GAME);
  }

  @Test(expected = ResourceNotFoundException.class)
  public void addPlayerToGame_resourceNotFound() {
    newPlayerDocument.append(ATTRIBUTE_PLAYER_ID, playerId);
    when(mongoDatabase.getCollection(COLLECTION_GAMES)).thenReturn(mockDocuments);
    when(mockDocuments
        .find(com.mongodb.client.model.Filters.eq(ATTRIBUTE_GAME_ID, gameId), Game.class))
        .thenReturn(games);
    when(games.sort(any())).thenReturn(games);
    when(games.first()).thenReturn(null);

    mongoDbGameDao.addPlayer(PLAYER, gameId);
  }
}