package intuit.com.web.persistance;

import intuit.com.exceptions.IntuitDAOException;
import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.models.Player;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAOMap implements IDAO<Player> {

    Map<String, Player> persistence;
    private static DAOMap instance;

    private DAOMap(){
        persistence = new HashMap<>();
    }

    public static DAOMap getInstance(){
        if(instance == null){
            instance = new DAOMap();
        }
        return instance;
    }

    @Override
    public void AddAllEntities(List<Player> entities){
        Map<String, Player> intermidiateMap = new HashMap<>();
        entities.parallelStream().forEach(entity -> intermidiateMap.put(entity.getPlayerID(), entity));
        this.persistence.putAll(intermidiateMap);
    }

    @Override
    public List<Player> getEntities() {
        return this.persistence.values().stream().toList();
    }

    @Override
    public String AddEntity(@NonNull Player entity) throws IntuitDAOException {
        if (entity.getPlayerID() == null){
            throw new IntuitDAOException(String.format("provided player id is null. Player is: %s", entity.toString()));
        }
        persistence.put(entity.getPlayerID(), entity);
        return this.getEntityByID(entity.getPlayerID()).getPlayerID();
    }

    @Override

    public Player getEntityByID(@NonNull String id) {
        return this.persistence.get(id);
    }
}
