package intuit.com.web.importer;

import intuit.com.web.Mappers.PlayerDataPropertiesToPlayerMapper;
import intuit.com.web.importer.interfaces.IImporterService;
import intuit.com.web.persistance.PlayersRepositoryServiceMapImpl;
import intuit.com.web.persistance.interfaces.IPlayersRepositoryService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImporterService implements IImporterService {
    private final IPlayersRepositoryService repositoryService;

    public ImporterService() {
        this.repositoryService = new PlayersRepositoryServiceMapImpl();
    }

    @Override
    public void importPlayers(List<String[]> rawPlayers) {
        this.repositoryService.saveAllPlayers(
                rawPlayers.stream().
                        map(PlayerDataPropertiesToPlayerMapper::createPlayerFromFields).
                        filter(Objects::nonNull).
                        collect(Collectors.toList())
        );
    }
}
