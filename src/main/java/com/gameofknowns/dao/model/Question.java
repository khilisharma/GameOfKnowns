package com.gameofknowns.dao.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Question {

  private Map<String, Choice> choices;
  private String questionId;
  private String questionText;
  private Choice rightAnswer;
}
