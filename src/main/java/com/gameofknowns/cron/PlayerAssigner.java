package com.gameofknowns.cron;

import static com.gameofknowns.constants.StringConstants.ATTRIBUTE_MAX_PLAYERS_IN_GAME_LIMIT;

import com.gameofknowns.dao.GameDao;
import com.gameofknowns.dao.TokenDao;
import com.gameofknowns.dao.model.Game;
import com.gameofknowns.dao.model.Player;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

public class PlayerAssigner {

  @Inject
  private GameDao gameDao;
  @Inject
  private TokenDao tokenDao;

  /**
   * Fetches the open games and adds the player to any of those games
   */
  public void init() {
    //find player who is not in any game
    //call token table for entries where game id is empty , take that list and assign random game to those players
    List<Game> openGames = gameDao.getOpenGames();
    List<String> unassignedPlayers = tokenDao.unassignedPlayers();
    Set<String> asssignedPlayers = new HashSet<>();
    for (Game game : openGames) {
      int playersCount = game.getPlayers().size();
      for (String unassignedPlayer : unassignedPlayers) {
        if (playersCount < ATTRIBUTE_MAX_PLAYERS_IN_GAME_LIMIT && !asssignedPlayers
            .contains(unassignedPlayer)) {
          // add the player in token collection and game collection
          tokenDao.addPlayer("", unassignedPlayer);
          gameDao.addPlayer(Player.builder().playerId(unassignedPlayer).playerName("").build(),
              game.getGameId());
          asssignedPlayers.add(unassignedPlayer);
          playersCount--;
        }
      }
    }
  }

}
