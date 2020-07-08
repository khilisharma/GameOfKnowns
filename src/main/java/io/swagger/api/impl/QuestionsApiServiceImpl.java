package io.swagger.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.exception.IllegalAccessException;
import com.gameofknowns.dao.exception.ResourceNotFoundException;
import com.gameofknowns.dao.model.Choice;
import com.gameofknowns.dao.model.Question;
import io.swagger.api.NotFoundException;
import io.swagger.api.QuestionsApiService;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")
@AllArgsConstructor
public class QuestionsApiServiceImpl extends QuestionsApiService {

  @Inject
  private ObjectMapper mapper;
  @Inject
  private GameDao gameDao;

  @Override
  public Response getQuestion(@NotNull String playerId, @NotNull String gameId,
      SecurityContext securityContext) throws NotFoundException {
    try {
      final io.swagger.model.Question response = buildResponse(playerId, gameId);
      return Response.ok()
          .entity(mapper.writeValueAsString(response)).build();
    } catch (final ResourceNotFoundException ex) {
      return Response.noContent().build();
    } catch (final IllegalAccessException ex) {
      return Response.status(Status.fromStatusCode(403)).build();
    } catch (final Exception ex) {
      return Response.serverError().build();
    }

  }

  private io.swagger.model.Question buildResponse(@NotNull String playerId,
      @NotNull String gameId) {
    final Question question = gameDao.getQuestion(gameId, playerId);
    final Map<Integer, String> choices = new HashMap<>();
    for (Entry<String, Choice> choice : question.getChoices().entrySet()) {
      choices.put(Integer.parseInt(choice.getValue().getChoiceId()),
          choice.getValue().getText());
    }
    final io.swagger.model.Question response = new io.swagger.model.Question();
    response.setQuestionText(question.getQuestionText());
    response.setQuestionId(question.getQuestionId());
    response.setChoices(choices);
    return response;
  }
}
