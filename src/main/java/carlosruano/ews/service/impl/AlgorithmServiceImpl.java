package carlosruano.ews.service.impl;

import carlosruano.ews.dto.PlayerDTO;
import carlosruano.ews.dto.SeasonDTO;
import carlosruano.ews.entity.Prediction;
import carlosruano.ews.entity.Season;
import carlosruano.ews.entity.Team;
import carlosruano.ews.exception.EwsException;
import carlosruano.ews.repository.PlayerRepository;
import carlosruano.ews.repository.SeasonRepository;
import carlosruano.ews.repository.TeamRepository;
import carlosruano.ews.service.AlgorithmService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
@Transactional
public class AlgorithmServiceImpl implements AlgorithmService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private SeasonRepository seasonRepository;


    // Temporada pasada
    //private final Season lastSeason;
    // Temporada actual
    //private final SeasonDTO season;
    // Mapa con los jugadores, y las horas que llevan acumulada esta temporada
    //private final Map<PlayerDTO, Long> playersPlayedHours;

    //Selección de dos equipos
    // A = GF --> 0
    // B = GA --> 1
    // C = FORM --> 2
    // D = WINS --> 3
    // E = POS --> 4

    public String getTeamToWin(Long teamId, Long visitorTeamId){

        Restea

        List<Double> paramsHomeTeam = homogenizeDates(homeTeam);
        List<Double> paramsVisitorTeam = homogenizeDates(visitorTeam);

        double valorHomeTeam = calculateValor(paramsHomeTeam);
        double valorVisitorTeam = calculateValor(paramsVisitorTeam);

        return predictionMatch(valorHomeTeam, valorVisitorTeam);
    }

    //Comparar sus estadísticas

    //una vez obtenidos los valores de cada equipo se saca la predicción
    private String predictionMatch(Double valor1, Double valor2){

        double res = valor1-valor2;
        String result = String.valueOf(Prediction.DRAW);

        if(res<=0.85 && res>0.0){
            result = String.valueOf(Prediction.DRAW);
        }else if(res>0.85){
            result = String.valueOf(Prediction.WIN_HOME);
        }else if(res<0.0 && res >=-0.85){
            result = String.valueOf(Prediction.DRAW);
        }else if (res < 0.85){
            result = String.valueOf(Prediction.WIN_VISITOR);
        }
        return result;
    }

    //Cálculo del valor del equipo en ese momento del torneo
    private Double calculateValor(List<Double> params){

        return params.get(0)*0.2 + params.get(1)*0.2 + params.get(2)*0.3 +
                params.get(3)*0.2 + params.get(4)*0.1;
    }

    //método para homogeneizar los datos, ya que estos vienen globales y lo hemos generalizado
    //en valores E[0, 4]
    private List<Double> homogenizeDates(Team team){

        double gF = (double) (team.getGf()/team.getPlayed());
        double gA = (double) (team.getGa()/team.getPlayed());
        double realForm = formCalculate(team.getForm());
        double wins = (double) ((team.getWins()/team.getPlayed()) * 4);
        double pos = posCalculate(team.getPosition());;

        List<Double> homogenised = new ArrayList<Double>();
        homogenised.add(0, gF);
        homogenised.add(1, gA);
        homogenised.add(2, realForm);
        homogenised.add(3, wins);
        homogenised.add(4, pos);

        return homogenised;
    }
    //La forma viene en formato de 4 dígitos "WLDW" WIN DRAW LOST
    // 1 PUNTO PARA LA W, 0,5 PARA LA D, Y 0 PARA LA L
    private Double formCalculate(String form){

        double realForm = 0.0;
        int tam = form.length();
        for(int i = 0;i<tam;i++) {
            if (form.substring(0).contains("w")) {
                realForm = realForm + 1;
            }else if(form.substring(0).contains("d")){
                realForm = realForm + 0.5;
            }

            if (form.substring(1).contains("w")) {
                realForm = realForm + 1;
            }else if(form.substring(1).contains("d")){
                realForm = realForm + 0.5;
            }
            if (form.substring(2).contains("w")) {
                realForm = realForm + 1;
            }else if(form.substring(2).contains("d")){
                realForm = realForm + 0.5;
            }

            if (form.substring(3).contains("w")) {
                realForm = realForm + 1;
            }else if(form.substring(3).contains("d")){
                realForm = realForm + 0.5;
            }
        }
        return realForm;
    }

    //método para asignar un valor a la position actual del equipo
    // con valores E[0, 4]
    private Integer posCalculate(Integer position){
        int realPos = 0;
        if(position > 0 && position <= 5){
            realPos = 4;
        }else if(position > 5 && position <= 10){
            realPos = 3;
        }else if(position > 10 && position <= 15){
            realPos = 2;
        }else if(position > 15 && position <= 20){
            realPos = 1;
        }
        return realPos;
    }

    @Override
    public void saveAll() throws EwsException {

    }

    @Override
    public void cleanSeason() throws EwsException {

    }
    //Generar un resultado predictivo



}
