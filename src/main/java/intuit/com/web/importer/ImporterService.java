package intuit.com.web.importer;

import intuit.com.web.Mappers.PlayerDataPropertiesToPlayerMapper;
import intuit.com.web.importer.interfaces.IImporterService;
import intuit.com.web.persistance.interfaces.IPlayersRepositoryService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ImporterService implements IImporterService {
    public final IPlayersRepositoryService repositoryService;

    public ImporterService(IPlayersRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Override
    public void importPlayers(List<String[]> rawPlayers) {
        this.repositoryService.saveAllPlayers(
                rawPlayers.stream()
                        .filter(Objects::nonNull)
                        .map(PlayerDataPropertiesToPlayerMapper::createPlayerFromFields)
                        .collect(Collectors.toList())
        );
    }
}
