package intuit.com.web.importer;

import intuit.com.web.importer.interfaces.IImporterService;
import intuit.com.web.persistance.PlayersRepositoryServiceMapImpl;
import intuit.com.web.persistance.interfaces.IPlayersRepositoryService;
import intuit.com.web.persistance.models.Player;
import intuit.com.web.persistance.models.PlayerSidePreference;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
                        map(this::createPlayerFromFields).
                        filter(Objects::nonNull).
                        collect(Collectors.toList())
        );
    }

    private Player createPlayerFromFields(String[] playerProperties) {
        Player player = new Player();

        player.setPlayerID(playerProperties[0]);
        player.setBirthYear(actOnIntField(playerProperties[1]));
        player.setBirthMonth(actOnIntField(playerProperties[2]));
        player.setBirthDay(actOnIntField(playerProperties[3]));
        player.setBirthCountry(playerProperties[4]);
        player.setBirthState(playerProperties[5]);
        player.setBirthCity(playerProperties[6]);
        player.setDeathYear(actOnIntField(playerProperties[7]));
        player.setDeathMonth(actOnIntField(playerProperties[8]));
        player.setDeathDay(actOnIntField(playerProperties[9]));
        player.setDeathCountry(playerProperties[10]);
        player.setDeathState(playerProperties[11]);
        player.setDeathCity(playerProperties[12]);
        player.setNameFirst(playerProperties[13]);
        player.setNameLast(playerProperties[14]);
        player.setNameGiven(playerProperties[15]);
        player.setWeight(actOnIntField(playerProperties[16]));
        player.setHeight(actOnIntField(playerProperties[17]));
        player.setBats(actOnPlayerSideEnumField(playerProperties[18]));
        player.setShots(actOnPlayerSideEnumField(playerProperties[19]));
        player.setDebut(actOnDateField(playerProperties[20]));
        player.setFinalGame(actOnDateField(playerProperties[21]));
        player.setRetroID(playerProperties[22]);
        player.setBbrefID(playerProperties[23]);

        return player;
    }

    private Integer actOnIntField(String integerTypePropertyValue) {
        return Optional.ofNullable(integerTypePropertyValue).filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .orElse(null);
    }

    private PlayerSidePreference actOnPlayerSideEnumField(String enumTypePropertyValue) {
        return Optional.ofNullable(enumTypePropertyValue).filter(str -> !str.isEmpty())
                .map(PlayerSidePreference::valueOf)
                .orElse(null);
    }

    private LocalDate actOnDateField(String datePropertyValue) {
        return Optional.ofNullable(datePropertyValue).filter(str -> !str.isEmpty())
                .map(LocalDate::parse)
                .orElse(null);
    }



}
