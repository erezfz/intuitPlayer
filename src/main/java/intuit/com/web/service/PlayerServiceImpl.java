package intuit.com.web.service;

import intuit.com.web.persistance.PlayersRepositoryServiceMapImpl;
import intuit.com.web.persistance.interfaces.IPlayersRepositoryService;
import intuit.com.web.persistance.models.Player;
import intuit.com.web.service.interfaces.IPlayerService;

import java.util.List;
import java.util.Optional;

public class PlayerServiceImpl implements IPlayerService {
    IPlayersRepositoryService repository = new PlayersRepositoryServiceMapImpl();
    @Override
    public List<Player> getPlayers() {
        return repository.getAllPlayers();
    }

    @Override
    public Optional<Player> getPlayerById(String playerID) {
       Player player = repository.getPlayerById(playerID);
       return Optional.ofNullable(player);
    }
}
