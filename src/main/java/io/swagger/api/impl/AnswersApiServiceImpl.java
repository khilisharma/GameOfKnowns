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

@AllArgsConstructor
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
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
    try {
      final Question question = questionsDao.getQuestion(body.getQuestionId());
      if (question.getRightAnswer().getChoiceId().equals(body.getAnswerId())) {
        gameDao.advancePlayer(body.getGameId(), body.getPlayerId());
        return Response.ok().entity(SubmitAnswerResponse.RIGHT.toString()).build();
      } else {
        return Response.ok().entity(SubmitAnswerResponse.WRONG.toString()).build();
      }
    } catch (final ResourceNotFoundException ex) {
      return Response.noContent().build();
    } catch (final IllegalArgumentException ex) {
      return Response.status(Status.fromStatusCode(403)).build();
    } catch (final Exception ex) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
