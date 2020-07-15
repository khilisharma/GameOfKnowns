package io.swagger.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.QuestionsDao;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.Question;
import io.swagger.api.AnswersApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.SubmitAnswerRequest;
import io.swagger.model.SubmitAnswerResponse;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
@Slf4j
public class AnswersApiServiceImpl extends AnswersApiService {

  @Inject
  private QuestionsDao questionsDao;
  @Inject
  private GameDao gameDao;
  @Inject
  private ObjectMapper mapper;

  @Override
  public Response submitAnswer(SubmitAnswerRequest body, SecurityContext securityContext)
      throws NotFoundException {

    // call question to know correct answer
    final String gameId = body.getGameId();
    final String choiceId = body.getAnswerId();
    final String questionId = body.getQuestionId();
    final String playerId = body.getPlayerId();
    try {
      final Question question = questionsDao.getQuestion(questionId);
      if (!gameDao.isPlayerInTheGame(gameId, playerId)) {
        return Response.status(Status.FORBIDDEN).build();
      }
      gameDao.countAnswerChoice(questionId, choiceId, gameId);
      if (question.getRightAnswer().get("id").equals(choiceId)) {
        gameDao.advancePlayer(gameId, playerId);
        return Response.ok().entity(SubmitAnswerResponse.RIGHT.toString()).build();
      } else {
        return Response.ok().entity(SubmitAnswerResponse.WRONG.toString()).build();
      }
    } catch (final ResourceNotFoundException ex) {
      log.debug("Resouce not found, questionId: {}, gameId: {}, playerId: {}, choiceId: {}",
          questionId, gameId, playerId, choiceId, ex);
      return Response.status(Status.NOT_FOUND).entity("Invalid request parameters!").build();
    } catch (final Exception ex) {
      log.error("Something went wrong!!", ex);
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
