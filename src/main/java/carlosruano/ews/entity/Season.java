package carlosruano.ews.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder=true)
@Entity
@Table(name = "season", uniqueConstraints = @UniqueConstraint(columnNames = {"yearSeason"}))
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private Long yearSeason;
    @OneToMany
    private List<Team> teams;
}
