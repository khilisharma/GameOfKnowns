package com.gameofknowns.dao;

import com.gameofknowns.dao.model.Player;
import com.gameofknowns.dao.model.PlayerStatus;
import com.gameofknowns.dao.model.PlayerStatusEnum;
import java.util.List;

public class MockTokenDao implements TokenDao {

  @Override
  public String addPlayer(String playerId, String playerName) {
    return null;
  }

  @Override
  public void updateToken(String playerId, String gameId) {

  }

  @Override
  public PlayerStatus getStatus(String tokenId) {
    return PlayerStatus.builder().status(PlayerStatusEnum.WAIT).build();
  }

  @Override
  public List<Player> unassignedPlayers() {
    return null;
  }
}
