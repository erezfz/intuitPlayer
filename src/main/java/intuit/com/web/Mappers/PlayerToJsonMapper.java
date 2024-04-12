package intuit.com.web.Mappers;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import intuit.com.web.persistance.models.Player;

import java.util.List;

public final class PlayerToJsonMapper {
    private static PlayerToJsonMapper instance;
    private final ObjectMapper mapper;

    private PlayerToJsonMapper() {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    public JsonNode mapPlayerToJson(Player player) {
        mapper.findAndRegisterModules();
        return mapper.valueToTree(player);
    }

    public List<JsonNode> mapPlayersToJson(List<Player> players) {
        return players.parallelStream().map(this::mapPlayerToJson).toList();
    }

    public static PlayerToJsonMapper getMapper(){
        if (instance == null){
            instance = new PlayerToJsonMapper();
        }
        return instance;
    }
}
