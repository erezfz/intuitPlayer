package intuit.com.web.persistance.models;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Player {
    @NonNull
    private String playerID;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private String birthCountry;
    private String birthState;
    private String birthCity;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private String deathCountry;
    private String deathState;
    private String deathCity;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer weight;
    private Integer height;
    private PlayerSidePreference bats;
    private PlayerSidePreference shots;
    private LocalDate debut;
    private LocalDate finalGame;
    private String retroID;
    private String bbrefID;

}
