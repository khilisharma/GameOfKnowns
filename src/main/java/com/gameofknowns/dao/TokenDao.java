package com.gameofknowns.dao;

import com.gameofknowns.dao.model.PlayerStatus;
import java.util.List;

public interface TokenDao {

  String addPlayer(String playerName, String playerId);

  PlayerStatus getStatus(String tokenId);

  List<String> unassignedPlayers();
}
