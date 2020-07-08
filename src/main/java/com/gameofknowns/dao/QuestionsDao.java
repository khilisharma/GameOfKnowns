package com.gameofknowns.dao;


import com.gameofknowns.dao.model.Question;

public interface QuestionsDao {

  Question getQuestion(String questionId);
}
