package carlosruano.ews.dto;

import carlosruano.ews.entity.Player;
import carlosruano.ews.entity.Season;
import carlosruano.ews.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamDTO {

    private Long id;
    private String club;
    private @NotBlank Integer GF;
    private @NotBlank Integer GC;
    private @NotBlank Integer yellowCard;
    private @NotBlank Integer redCard;
    private @NotBlank Integer position;
    private @NotBlank String form;
    private @NotBlank Integer played;
    private @NotBlank Integer won;
    private @NotBlank Integer drawn;
    private @NotBlank Integer lost;
    private List<PlayerDTO> players;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.club = team.getTeam();
        this.GF = team.getGf();
        this.GC = team.getGa();
        this.yellowCard = team.getYellowCard();
        this.redCard = team.getRedCard();
        this.position = team.getPosition();
        this.form = team.getForm();
        this.played = team.getPlayed();
        this.won = team.getWins();
        this.drawn = team.getDraws();
        this.lost = team.getLosses();
        this.players = team.getPlayers().stream().map(PlayerDTO::new).collect(Collectors.toList());
    }
}
