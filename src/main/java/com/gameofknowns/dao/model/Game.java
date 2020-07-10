package com.gameofknowns.dao.model;

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
      @BsonProperty("gameFlag") Boolean gameFlag,
      @BsonProperty("players") List<Map<String, String>> players,
      @BsonProperty("firstChoiceCount") Integer firstChoiceCount,
      @BsonProperty("secondChoiceCount") Integer secondChoiceCount,
      @BsonProperty("thirdChoiceCount") Integer thirdChoiceCount

  ) {
    this.gameId=gameId;
    this.round=round;
    this.questionId=questionId;
    this.gameFlag=gameFlag;
    this.players=players;
    this.firstChoiceCount=firstChoiceCount;
    this.secondChoiceCount=secondChoiceCount;
    this.thirdChoiceCount=thirdChoiceCount;
  }

  private String gameId;
  private String round;
  private String questionId;
  private boolean gameFlag;
  private List<Map<String, String>> players;
  private int firstChoiceCount;
  private int secondChoiceCount;
  private int thirdChoiceCount;
}
