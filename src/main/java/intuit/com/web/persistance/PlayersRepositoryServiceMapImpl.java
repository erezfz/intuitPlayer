package intuit.com.web.persistance;

import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.interfaces.IPlayersRepositoryService;
import intuit.com.web.persistance.models.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PlayersRepositoryServiceMapImpl implements IPlayersRepositoryService {
    private final IDAO<Player> dao = DAOMap.getInstance();

    @Override
    public void saveAllPlayers(Iterable<Player> players) throws ExceptionInInitializerError{
        dao.AddAllEntities(players);
    }

    @Override
    public Player getPlayerById(String id) {
        return dao.getEntityByID(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return StreamSupport.stream(dao.getEntities().spliterator(), true).collect(Collectors.toList());
    }
}
