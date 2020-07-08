package com.gameofknows.dao;

import io.swagger.model.Question;

public interface QuestionsDao {
  Question getQuestion(String questionId);
  String getRightAnswer(String questionId);
}
