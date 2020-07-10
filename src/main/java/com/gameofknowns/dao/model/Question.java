package com.gameofknowns.dao.model;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Data
@Builder
public class Question {

  @BsonCreator
  public Question(
      @BsonProperty("choices") Map<String, String> choices,
      @BsonProperty("questionId") String questionId,
      @BsonProperty("questionText") String questionText,
      @BsonProperty("rightAnswer") Map<String, String> rightAnswer
  ) {
    this.choices = choices;
    this.rightAnswer = rightAnswer;
    this.questionText = questionText;
    this.questionId = questionId;
  }

  private Map<String, String> choices;
  private String questionId;
  private String questionText;
  private Map<String, String> rightAnswer;
}
