package com.gameofknowns.dao.model;

import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Data
@Builder
@Setter
public class Game {

  @BsonCreator
  public Game(
      @BsonProperty("gameId") String gameId,
      @BsonProperty("round") String round,
      @BsonProperty("questionId") String questionId,
      @BsonProperty("gameStatus") String gameStatus,
      @BsonProperty("players") List<Map<String, String>> players,
      @BsonProperty("firstChoiceCount") Integer firstChoiceCount,
      @BsonProperty("secondChoiceCount") Integer secondChoiceCount,
      @BsonProperty("thirdChoiceCount") Integer thirdChoiceCount,
      @BsonProperty("createdAt") String createdAt

  ) {
    this.gameId=gameId;
    this.round=round;
    this.questionId=questionId;
    this.gameStatus = this.gameStatus;
    this.players=players;
    this.firstChoiceCount=firstChoiceCount;
    this.secondChoiceCount=secondChoiceCount;
    this.thirdChoiceCount=thirdChoiceCount;
    this.createdAt=createdAt;
  }

  private String gameId;
  private String round;
  private String questionId;
  private String gameStatus;
  private List<Map<String, String>> players;
  private int firstChoiceCount;
  private int secondChoiceCount;
  private int thirdChoiceCount;
  private String createdAt;
}
