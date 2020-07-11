package com.gameofknowns.dao.model;

import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Data
@Builder
public class Player {

  public Player(@BsonProperty("playerId") String playerId,
      @BsonProperty("playerName") String playerName) {
    this.playerId = playerId;
    this.playerName = playerName;
  }

  private String playerId;
  private String playerName;
}
