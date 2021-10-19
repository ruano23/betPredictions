package carlosruano.ews.service;

import carlosruano.ews.entity.Team;
import carlosruano.ews.exception.EwsException;

public interface AlgorithmService {
        void saveAll() throws EwsException;
        void cleanSeason() throws EwsException;
        String getTeamToWin(Team homeTeam, Team visitorTeam);
}
