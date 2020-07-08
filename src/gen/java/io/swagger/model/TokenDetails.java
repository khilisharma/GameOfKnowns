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
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

/**
 * TokenDetails
 */
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.JavaJerseyServerCodegen", date = "2020-07-07T05:47:13.537Z[GMT]")public class TokenDetails   {
  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    JOINED("JOINED"),
    
    WAIT("WAIT");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("status")
  private StatusEnum status = null;

  @JsonProperty("playerId")
  private String playerId = null;

  @JsonProperty("gameId")
  private String gameId = null;

  public TokenDetails status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   **/
  @JsonProperty("status")
  @Schema(description = "")
  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }

  public TokenDetails playerId(String playerId) {
    this.playerId = playerId;
    return this;
  }

  /**
   * Get playerId
   * @return playerId
   **/
  @JsonProperty("playerId")
  @Schema(description = "")
  public String getPlayerId() {
    return playerId;
  }

  public void setPlayerId(String playerId) {
    this.playerId = playerId;
  }

  public TokenDetails gameId(String gameId) {
    this.gameId = gameId;
    return this;
  }

  /**
   * Get gameId
   * @return gameId
   **/
  @JsonProperty("gameId")
  @Schema(description = "")
  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TokenDetails tokenDetails = (TokenDetails) o;
    return Objects.equals(this.status, tokenDetails.status) &&
        Objects.equals(this.playerId, tokenDetails.playerId) &&
        Objects.equals(this.gameId, tokenDetails.gameId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, playerId, gameId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TokenDetails {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    playerId: ").append(toIndentedString(playerId)).append("\n");
    sb.append("    gameId: ").append(toIndentedString(gameId)).append("\n");
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
