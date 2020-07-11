package com.gameofknowns.dao.model;

import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Builder
@Data
public class Token {

  @BsonCreator
  public Token(
      @BsonProperty("tokenId") String tokenId,
      @BsonProperty("gameId") String gameId,
      @BsonProperty("playerId") String playerId,
      @BsonProperty("playerName") String playerName
  ) {
    this.tokenId = tokenId;
    this.gameId = gameId;
    this.playerId = playerId;
    this.playerName = playerName;
  }

  private String tokenId;
  private String gameId;
  private String playerId;
  private String playerName;
}
