package com.gameofknowns.dao;


import com.gameofknowns.dao.model.Choice;
import com.gameofknowns.dao.model.Question;
import java.util.HashMap;

public class MockQuestionsDao implements QuestionsDao {

  @Override
  public Question getQuestion(String questionId) {
    return new Question(new HashMap<>(), "123", "what?", new Choice("123", "abc"));
  }
}
