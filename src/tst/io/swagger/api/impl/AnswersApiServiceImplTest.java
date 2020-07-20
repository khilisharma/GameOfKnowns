package io.swagger.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.Question;
import io.swagger.api.NotFoundException;
import io.swagger.model.SubmitAnswerRequest;
import io.swagger.model.SubmitAnswerResponse;
import java.util.Collections;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnswersApiServiceImplTest {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String STATUS = "status";
  private static final String GAME_ID = "mxndkjqwhe123";
  private static final String QUESTION_ID = "123asd";
  private static final String QUESTION_TEXT = "I'm a sample question?";
  private static final String PLAYER_ID = "123atyhngbh";
  private static final String ANSWER_ID = "123";
  private static final Question QUESTION = Question.builder()
                                                   .questionText(QUESTION_TEXT)
                                                   .rightAnswer(Collections
                                                       .singletonMap("id", ANSWER_ID))
                                                   .questionId(QUESTION_ID)
                                                   .build();

  @Mock
  private QuestionsDao questionsDao;
  @Mock
  private GameDao gameDao;

  private AnswersApiServiceImpl answersApiService;

  private SubmitAnswerRequest submitAnswerRequest = new SubmitAnswerRequest();

  @Before
  public void setUp() throws Exception {
    answersApiService = new AnswersApiServiceImpl(questionsDao, gameDao, MAPPER);
    submitAnswerRequest.setQuestionId(QUESTION_ID);
    submitAnswerRequest.setGameId(GAME_ID);
    submitAnswerRequest.setPlayerId(PLAYER_ID);
  }

  @Test
  public void testSubmitAnswer_200CorrectAnswer() throws NotFoundException, JsonProcessingException {

    submitAnswerRequest.setAnswerId(ANSWER_ID);

    when(questionsDao.getQuestion(eq(QUESTION_ID))).thenReturn(QUESTION);
    when(gameDao.isPlayerInTheGame(eq(GAME_ID), eq(PLAYER_ID))).thenReturn(true);


    final Response response = answersApiService.submitAnswer(submitAnswerRequest, null);
    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(Collections.singletonMap(STATUS,
        SubmitAnswerResponse.RIGHT.toString()))).build();

    assertEquals("assert status", expected.getStatus(), response.getStatus());
    assertEquals("assert entity", expected.getEntity(), response.getEntity());
  }

  @Test
  public void testSubmitAnswer_200WrongAnswer() throws NotFoundException, JsonProcessingException {

    submitAnswerRequest.setAnswerId("45");

    when(questionsDao.getQuestion(eq(QUESTION_ID))).thenReturn(QUESTION);
    when(gameDao.isPlayerInTheGame(eq(GAME_ID), eq(PLAYER_ID))).thenReturn(true);


    final Response response = answersApiService.submitAnswer(submitAnswerRequest, null);
    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(Collections.singletonMap(STATUS,
        SubmitAnswerResponse.WRONG.toString()))).build();

    assertEquals("assert status", expected.getStatus(), response.getStatus());
    assertEquals("assert entity", expected.getEntity(), response.getEntity());
  }

  @Test
  public void testSubmitAnswer_403() throws NotFoundException, JsonProcessingException {
    submitAnswerRequest.setAnswerId("45");

    when(questionsDao.getQuestion(eq(QUESTION_ID))).thenReturn(QUESTION);
    when(gameDao.isPlayerInTheGame(eq(GAME_ID), eq(PLAYER_ID))).thenReturn(false);


    final Response response = answersApiService.submitAnswer(submitAnswerRequest, null);
    final Response expected = Response.status(Status.FORBIDDEN).build();

    assertEquals("assert status", expected.getStatus(), response.getStatus());
    assertEquals("assert entity", expected.getEntity(), response.getEntity());
  }

  @Test
  public void testSubmitAnswer_404() throws NotFoundException {
    submitAnswerRequest.setAnswerId("45");
    when(questionsDao.getQuestion(anyString())).thenThrow(ResourceNotFoundException.class);

    final Response response = answersApiService.submitAnswer(submitAnswerRequest, null);

    final Response expected = Response.status(Status.NOT_FOUND).entity("Invalid request parameters!").build();

    assertEquals("assert status", expected.getStatus(), response.getStatus());
    assertEquals("assert entity", expected.getEntity(), response.getEntity());
  }

}