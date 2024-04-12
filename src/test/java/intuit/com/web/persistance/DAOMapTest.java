package intuit.com.web.persistance;

import intuit.com.AppConfig;
import intuit.com.exceptions.IntuitDAOException;
import intuit.com.web.Mappers.PlayerDataPropertiesToPlayerMapper;
import intuit.com.web.importer.utils.PlayerFileReader;
import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class DAOMapTest {
    IDAO<Player> dao;
    List<String[]> playersData;


    @BeforeEach
    void setTest() throws IOException, URISyntaxException {
        Properties propertiesMock = mock(Properties.class);
        try (MockedStatic<AppConfig> mockedStatic = mockStatic(AppConfig.class)){
            when(propertiesMock.getProperty("data.file.players.name")).thenReturn("fixture.csv");
            mockedStatic.when(AppConfig::getProperties).thenReturn(propertiesMock);
            playersData = PlayerFileReader.getPlayersData();
            dao= DAOMap.getInstance();
        }
    }

    @Test
    void daoAddAllEntities(){
        final List<Player> players = playersData.stream().map(PlayerDataPropertiesToPlayerMapper::createPlayerFromFields).toList();
        assertDoesNotThrow(() -> dao.AddAllEntities(players));
        assertEquals(playersData.size(), dao.getEntities().size());
    }

    @Test
    void addEmptyEntity(){
        assertThrows(NullPointerException.class, () -> dao.AddEntity(null));
    }

    @Test
    void addEntityWithoutID(){
        Player player = new Player();
        assertThrows(IntuitDAOException.class, () ->dao.AddEntity(player));
    }

    @Test
    void getEntityByIdExisting() {
        final List<Player> players = playersData.stream().map(PlayerDataPropertiesToPlayerMapper::createPlayerFromFields).toList();
        String lastPlayerID = players.getLast().getPlayerID();
        try{
            dao.AddAllEntities(players);
            assertEquals(players.getLast(), dao.getEntityByID(lastPlayerID));
        } catch (IntuitDAOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getNullByIdNonExisting() {
        assertNull(dao.getEntityByID("abcd"));
    }
}