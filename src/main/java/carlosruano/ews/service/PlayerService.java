package carlosruano.ews.service;

import carlosruano.ews.entity.Player;
import carlosruano.ews.exception.EwsException;

public interface PlayerService {
    Player findPlayerByNumberAndTeam(Long numberTeam, String team) throws EwsException;
}
