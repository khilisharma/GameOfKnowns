package com.gameofknowns.dao;

import com.gameofknowns.dao.model.PlayerStatus;
import com.gameofknowns.dao.model.PlayerStatusEnum;
import java.util.List;

public class MockTokenDao implements TokenDao {

  @Override
  public String addPlayer(String playerName, String playerId) {
    return "123123123yiuashdjkasd";
  }

  @Override
  public PlayerStatus getStatus(String tokenId) {
    return PlayerStatus.builder().status(PlayerStatusEnum.WAIT).build();
  }

  @Override
  public List<String> unassignedPlayers() {
    return null;
  }
}
