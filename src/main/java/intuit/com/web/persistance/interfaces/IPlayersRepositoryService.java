package intuit.com.web.persistance.interfaces;

import intuit.com.web.persistance.models.Player;

import java.util.List;

public interface IPlayersRepositoryService {
    void saveAllPlayers(List<Player> players);
    Player getPlayerById(String id);
    List<Player> getAllPlayers();
}
