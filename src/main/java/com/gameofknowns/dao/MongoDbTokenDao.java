package com.gameofknowns.dao;

import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_GAME_ID;
import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_TOKEN_ID;
import static com.gameofknowns.constants.StringConstants.COLLECTION_TOKENS;
import static com.mongodb.client.model.Filters.eq;

import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.PlayerStatus;
import com.gameofknowns.dao.model.PlayerStatusEnum;
import com.gameofknowns.dao.model.Token;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Singleton
@Builder
public class MongoDbTokenDao implements TokenDao {

  @Inject
  private MongoDatabase database;

  @Override
  public String addPlayer(String playerName, String playerId) {
    UUID tokenId = UUID.randomUUID();
    Token newUserToken = Token.builder().tokenId(tokenId.toString()).playerId(playerId).gameId("").build();
    database.getCollection(COLLECTION_TOKENS, Token.class).insertOne(newUserToken);
    return tokenId.toString();
  }

  @Override
  // player Status has player id , game id , status enum JOINED or WAIT
  public PlayerStatus getStatus(String tokenId) {
    final Token allocatedGame = database.getCollection(COLLECTION_TOKENS)
        .find(eq(ATTRIBUTE_TOKEN_ID, tokenId), Token.class).first();
    if (allocatedGame == null) {
      throw new ResourceNotFoundException("Invalid tokenId");
    }
    final PlayerStatus.PlayerStatusBuilder builder = PlayerStatus.builder();
    if (allocatedGame.getGameId() == null) {
      builder.status(PlayerStatusEnum.WAIT);
    } else {
      builder.status(PlayerStatusEnum.JOINED);
      builder.gameId(Optional.of(allocatedGame.getGameId()));
      builder.playerId(Optional.of(allocatedGame.getPlayerId()));
    }
    return builder.build();
  }

  /**
   * returns the list of players who are not assigned to any game
   *
   */
  @Override
  public List<String> unassignedPlayers() {
    FindIterable<Token> unassigned = database.getCollection(COLLECTION_TOKENS)
        .find(eq(ATTRIBUTE_GAME_ID, ""), Token.class);
    List<String> toBeAssignedPlayers = new ArrayList<>();
    for( Token token : unassigned){
      toBeAssignedPlayers.add(token.getPlayerId());
    }
    return toBeAssignedPlayers;
  }
}
