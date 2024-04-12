package intuit.com.web.persistance;

import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.models.Player;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

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

    @SneakyThrows
    @Override
    public void AddAllEntities(Iterable<Player> entities) {
        StreamSupport.stream(entities.spliterator(), false).forEach(this::AddEntity);
    }

    @Override
    public Iterable<Player> getEntities() {
        return this.persistence.values();
    }

    @Override
    public String AddEntity(Player entity) {
        this.persistence.put(entity.getPlayerID(), entity);
        return this.getEntityByID(entity.getPlayerID()).getPlayerID();
    }

    @Override
    public Player getEntityByID(String id) {
        return this.persistence.get(id);
    }
}
