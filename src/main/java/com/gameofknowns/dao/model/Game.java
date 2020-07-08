package com.gameofknowns.dao.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Game {

  private String gameId;
  private String round;
  private String questionId;
  private boolean isBeingPlayed;
  private List<Player> players;
  private int firstChoiceCount;
  private int secondChoiceCount;
  private int thirdChoiceCount;
}
