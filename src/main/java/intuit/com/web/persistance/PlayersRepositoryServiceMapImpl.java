package intuit.com.web.persistance;

import intuit.com.exceptions.IntuitDAOException;
import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.interfaces.IPlayersRepositoryService;
import intuit.com.web.persistance.models.Player;

import java.util.List;

public class PlayersRepositoryServiceMapImpl implements IPlayersRepositoryService {
    private final IDAO<Player> dao = DAOMap.getInstance();

    @Override
    public void saveAllPlayers(List<Player> players) {
        try {
            dao.AddAllEntities(players);
        } catch (IntuitDAOException e) {
            System.out.printf("failure to save all the Players: %s \n %s", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Player getPlayerById(String id) {
        return dao.getEntityByID(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return dao.getEntities();
    }
}
