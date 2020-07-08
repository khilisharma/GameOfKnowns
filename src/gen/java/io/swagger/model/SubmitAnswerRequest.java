/*
 * Game Of Knowns - API
 * This API lets users play the trivia game called Game Of Knowns
 *
 * OpenAPI spec version: 1.0.0
 * Contact: khili22.sharma@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

/**
 * SubmitAnswerRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")public class SubmitAnswerRequest   {
  @JsonProperty("playerId")
  private String playerId = null;

  @JsonProperty("gameId")
  private String gameId = null;

  @JsonProperty("questionId")
  private String questionId = null;

  @JsonProperty("answerId")
  private String answerId = null;

  public SubmitAnswerRequest playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * Get playerId
   * @return playerId
   **/
  @JsonProperty("playerId")
  @Schema(required = true, description = "")
  @NotNull
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public SubmitAnswerRequest gameId(String gameId) {
    this.gameId = gameId;
    return this;
  }

  /**
   * Get gameId
   * @return gameId
   **/
  @JsonProperty("gameId")
  @Schema(required = true, description = "")
  @NotNull
  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public SubmitAnswerRequest questionId(String questionId) {
    this.questionId = questionId;
    return this;
  }

  /**
   * Get questionId
   * @return questionId
   **/
  @JsonProperty("questionId")
  @Schema(required = true, description = "")
  @NotNull
  public String getQuestionId() {
    return questionId;
  }

  public void setQuestionId(String questionId) {
    this.questionId = questionId;
  }

  public SubmitAnswerRequest answerId(String answerId) {
    this.answerId = answerId;
    return this;
  }

  /**
   * Get answerId
   * @return answerId
   **/
  @JsonProperty("answerId")
  @Schema(required = true, description = "")
  @NotNull
  public String getAnswerId() {
    return answerId;
  }

  public void setAnswerId(String answerId) {
    this.answerId = answerId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubmitAnswerRequest submitAnswerRequest = (SubmitAnswerRequest) o;
    return Objects.equals(this.playerId, submitAnswerRequest.playerId) &&
        Objects.equals(this.gameId, submitAnswerRequest.gameId) &&
        Objects.equals(this.questionId, submitAnswerRequest.questionId) &&
        Objects.equals(this.answerId, submitAnswerRequest.answerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerId, gameId, questionId, answerId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmitAnswerRequest {\n");
    
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    gameId: ").append(toIndentedString(gameId)).append("\n");
    sb.append("    questionId: ").append(toIndentedString(questionId)).append("\n");
    sb.append("    answerId: ").append(toIndentedString(answerId)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
