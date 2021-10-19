package carlosruano.ews.dto;

import carlosruano.ews.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDTO {

    private Long id;
    private String name;
    private Double physical;
    private Double hourPlayed;
    private Long number;

    public PlayerDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.physical = player.getPhysical();
        this.hourPlayed = player.getHourPlayed();
        this.number = player.getNumber();

    }
}
