package io.swagger.api.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.Question;
import io.swagger.api.NotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class QuestionsApiServiceImplTest {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final String gameId = "66534725";
  private static final String playerId = "73640983";
  private static final String QUESTION_ID = "123asd";
  private static final String QUESTION_TEXT = "I'm a sample question?";
  private static final Question QUESTION = Question.builder().questionText(QUESTION_TEXT)
      .questionId(QUESTION_ID).choices(Collections.singletonMap("1", "sample choice")).build();

  @Mock
  GameDao gameDao;

  private QuestionsApiServiceImpl questionsApiService;

  @Before
  public void setUp() throws Exception {
    questionsApiService = new QuestionsApiServiceImpl(MAPPER, gameDao);
  }

  @Test
  public void testGetQuestionWith200Response() throws NotFoundException, JsonProcessingException {
    when(gameDao.getQuestion(eq(gameId), eq(playerId))).thenReturn(QUESTION);

    final io.swagger.model.Question response = new io.swagger.model.Question();
    response.setQuestionId(QUESTION.getQuestionId());
    response.setQuestionText(QUESTION.getQuestionText());
    response.setChoices(Collections.singletonMap(1, "sample choice"));

    final Response expected = Response.ok().entity(MAPPER.writeValueAsString(response)).build();
    final Response actual = questionsApiService.getQuestion(playerId, gameId, null);

    assertEquals("assert Entity", expected.getEntity(), actual.getEntity());
    assertEquals("assert status", expected.getStatus(), actual.getStatus());
  }

  @Test
  public void testGetQuestionWith404() throws NotFoundException {
    when(gameDao.getQuestion(anyString(),anyString())).thenThrow(ResourceNotFoundException.class);

    final Response actual = questionsApiService.getQuestion("p1", "g1", null);

    assertEquals("assert status", 404, actual.getStatus());

  }

  @Test
  public void testGetQuestionWith204() throws NotFoundException {
    when(gameDao.getQuestion(anyString(), anyString())).thenThrow(IllegalStateException.class);

    final Response actual = questionsApiService.getQuestion("p1", "g1", null);

    assertEquals("assert status", 204, actual.getStatus());

  }
}