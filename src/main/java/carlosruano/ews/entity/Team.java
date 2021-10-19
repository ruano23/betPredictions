package carlosruano.ews.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String team;
    @NotBlank
    private Integer gf;
    @NotBlank
    private Integer ga;
    @NotBlank
    private Integer yellowCard;
    @NotBlank
    private Integer redCard;
    @NotBlank
    private Integer position;
    @NotBlank
    private String form;
    @NotBlank
    private Integer played;
    @NotBlank
    private Integer wins;
    @NotBlank
    private Integer draws;
    @NotBlank
    private Integer losses;
    @OneToMany
    private List<Player> players;

}
