package com.gameofknowns.dao;

import com.gameofknowns.dao.model.PlayerStatus;

public interface TokenDao {

  String addPlayer(String playerName, String playerId);

  PlayerStatus getStatus(String tokenId);
}
