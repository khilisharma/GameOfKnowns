package com.gameofknowns.dao;

import com.gameofknowns.dao.model.Player;
import com.gameofknowns.dao.model.PlayerStatus;
import java.util.List;

public interface TokenDao {

  String addPlayer( String playerId, String playerName);

  void updateToken(String playerId, String gameId);

  PlayerStatus getStatus(String tokenId);

  List<Player> unassignedPlayers();
}
