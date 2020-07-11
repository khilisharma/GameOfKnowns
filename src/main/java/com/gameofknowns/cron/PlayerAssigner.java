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
import lombok.AllArgsConstructor;

/**
 * Fetches the games that are open from the games collection and assign players to each game if the number of players is less than the players limit
 */
@AllArgsConstructor
public class PlayerAssigner {

  @Inject
  private GameDao gameDao;
  @Inject
  private TokenDao tokenDao;

  public void init() {
    //find player who is not in any game
    //call token table for entries where game id is empty , take that list and assign random game to those players
    List<Game> openGames = gameDao.getOpenGames();
    List<Player> unassignedPlayers = tokenDao.unassignedPlayers();
    Set<Player> asssignedPlayers = new HashSet<>();
    for (Game game : openGames) {
      int playersCount = game.getPlayers().size();
      for (Player unassignedPlayer : unassignedPlayers) {
        if (playersCount < ATTRIBUTE_MAX_PLAYERS_IN_GAME_LIMIT && !asssignedPlayers
            .contains(unassignedPlayer)) {
          // add the player in token collection and game collection
          tokenDao.updateToken(unassignedPlayer.getPlayerId(), game.getGameId());
          gameDao.addPlayer(Player.builder().playerId(unassignedPlayer.getPlayerId()).playerName("").build(),
              game.getGameId());
          asssignedPlayers.add(unassignedPlayer);
          playersCount--;
        }
      }
    }
  }

}
