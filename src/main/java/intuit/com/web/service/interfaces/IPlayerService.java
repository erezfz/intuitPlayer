package intuit.com.web.service.interfaces;

import intuit.com.web.persistance.models.Player;

import java.util.List;
import java.util.Optional;

public interface IPlayerService {
    List<Player> getPlayers();
    Optional<Player> getPlayerById(String playerID);
}
