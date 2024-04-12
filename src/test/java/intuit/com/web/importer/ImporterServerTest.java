package intuit.com.web.importer;

import intuit.com.AppConfig;
import intuit.com.web.importer.utils.PlayerFileReader;
import intuit.com.web.persistance.DAOMap;
import intuit.com.web.persistance.PlayersRepositoryServiceMapImpl;
import intuit.com.web.persistance.interfaces.IDAO;
import intuit.com.web.persistance.models.Player;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImporterServerTest {

    static List<String[]> playersTokenizedData;


    @Mock
    private PlayersRepositoryServiceMapImpl repositoryServiceMock;

    @InjectMocks
    private ImporterService importerService;

    @BeforeAll
    static void setUp() throws IOException, URISyntaxException {
        Properties propertiesMock = mock(Properties.class);
        DAOMap playersDAOMock = mock(DAOMap.class);
        try (MockedStatic<AppConfig> mockedStatic = mockStatic(AppConfig.class)){
            when(propertiesMock.getProperty("data.file.players.name")).thenReturn("fixture.csv");
            mockedStatic.when(AppConfig::getProperties).thenReturn(propertiesMock);
            playersTokenizedData = PlayerFileReader.getPlayersData();
        }
    }

    @Test
    void importerServerPersistsAllTest() {
        ArgumentCaptor<List<Player>> captor = ArgumentCaptor.forClass(List.class);
        importerService.importPlayers(playersTokenizedData);
        verify(repositoryServiceMock, times(1)).saveAllPlayers(captor.capture());
        assertEquals(playersTokenizedData.size(), captor.getValue().size(), "Incorrect number of players");
    }

    @Test
    void importerServiceNotPersistNullEntitiesTest() {
        ArgumentCaptor<List<Player>> captor = ArgumentCaptor.forClass(List.class);
        final List<String[]> cpPlayersTokens = new java.util.ArrayList<>(playersTokenizedData.stream().map(SerializationUtils::clone).toList());
//        added a null element which should be removed by the importer service, and not moved to the repository
        cpPlayersTokens.add(null);
        importerService.importPlayers(cpPlayersTokens);
        verify(repositoryServiceMock, times(1)).saveAllPlayers(captor.capture());
        assertEquals(playersTokenizedData.size(), captor.getValue().size(), "Incorrect number of players");
    }


}
