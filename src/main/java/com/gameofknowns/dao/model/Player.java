package com.gameofknowns.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Player {

  private String playerId;
  private String playerName;
}
