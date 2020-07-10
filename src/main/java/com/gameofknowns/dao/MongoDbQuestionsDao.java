package com.gameofknowns.dao;

import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_QUESTION_ID;
import static com.gameofknowns.constants.StringConstants.COLLECTION_QUESTIONS;
import static com.mongodb.client.model.Filters.eq;

import com.gameofknowns.dao.model.Question;
import com.mongodb.client.MongoDatabase;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Builder;


@AllArgsConstructor
@Builder
@Singleton
public class MongoDbQuestionsDao implements QuestionsDao {

  @Inject
  private MongoDatabase database;

  @Override
  public Question getQuestion(String questionId) {
    final Question question = database.getCollection(COLLECTION_QUESTIONS)
        .find(eq(ATTRIBUTE_QUESTION_ID, questionId), Question.class).first();  // exclude question id

    return question;
  }
}
