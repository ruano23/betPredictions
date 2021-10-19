package carlosruano.ews;

import carlosruano.ews.entity.Team;
import carlosruano.ews.repository.PlayerRepository;
import carlosruano.ews.repository.SeasonRepository;
import carlosruano.ews.repository.TeamRepository;
import carlosruano.ews.service.AlgorithmService;
import carlosruano.ews.service.impl.AlgorithmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

/**
 * Clase para test de {@link PredictionTest}
 */
@Tag("PredictionTest")
public class PredictionTest {

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private SeasonRepository seasonRepository;

    private AlgorithmService algorithmService;

    @BeforeEach
    void setUp(){
        algorithmService = new AlgorithmServiceImpl(playerRepository, teamRepository, seasonRepository);
    }

    @Test
    public void winTheGame() {
        String result = algorithmService.getTeamToWin(createTeam(), createVisitorTeam());
        result.trim();
    }

    private Team createVisitorTeam() {
        Team teamVisitor = new Team();
        teamVisitor.setTeam("Real Madrid");
        teamVisitor.setDraws(4);
        teamVisitor.setForm("wwdl");
        teamVisitor.setGa(20);
        teamVisitor.setGf(30);
        teamVisitor.setLosses(3);
        teamVisitor.setPosition(5);
        teamVisitor.setWins(7);
        teamVisitor.setPlayed(14);
        teamVisitor.setId(1L);
        teamVisitor.setRedCard(1);
        teamVisitor.setYellowCard(6);
        teamVisitor.setPlayers(new ArrayList<>());

        return teamVisitor;
    }

    private Team createTeam() {
        Team teamLocal = new Team();
        teamLocal.setTeam("Reaasd");
        teamLocal.setDraws(2);
        teamLocal.setForm("wwww");
        teamLocal.setGa(12);
        teamLocal.setGf(45);
        teamLocal.setLosses(0);
        teamLocal.setPosition(1);
        teamLocal.setWins(13);
        teamLocal.setPlayed(14);
        teamLocal.setId(1L);
        teamLocal.setRedCard(1);
        teamLocal.setYellowCard(6);
        teamLocal.setPlayers(new ArrayList<>());
        return teamLocal;
    }


}
