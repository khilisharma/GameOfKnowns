package com.gameofknows.dao.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Question {
  static class Choice {
    String choiceId;
    String text;
  }
  private Map<String, Choice> choices;
  private String questionId;
  private String questionText;
  private Choice rightAnswer;
}
