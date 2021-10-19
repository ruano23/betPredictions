package carlosruano.ews.dto;

import carlosruano.ews.entity.Season;
import carlosruano.ews.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeasonDTO {

    private Long id;
    private Long yearSeason;
    private List<TeamDTO> teams;

    public SeasonDTO(Season season) {
        this.id = season.getId();
        this.yearSeason = season.getYearSeason();
        this.teams = season.getTeams().stream().map(TeamDTO::new).collect(Collectors.toList());

    }
}
