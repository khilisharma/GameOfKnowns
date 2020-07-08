package com.gameofknowns.dao.model;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class PlayerStatus {

  Optional<String> gameId;
  Optional<String> playerId;
  PlayerStatusEnum status;
}
