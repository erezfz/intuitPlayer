package intuit.com.web.importer;

import intuit.com.web.importer.utils.PlayerFileReader;
import intuit.com.web.persistance.PlayersRepositoryServiceMapImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class ImporterRunner {

    private final ImporterService playerImportService;

    public ImporterRunner() {
        this.playerImportService = new ImporterService(new PlayersRepositoryServiceMapImpl());
    }

    public void run() throws IOException, URISyntaxException {
        final List<String[]> playersData = PlayerFileReader.getPlayersData();
        this.playerImportService.importPlayers(playersData);
    }


}
