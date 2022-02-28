package dadsgame.businessapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "game")
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private String name;
  private String publisher;
  private String developer;
  private String description;
  private double price;

  @ManyToMany
  @JoinTable(name = "game_on",
          joinColumns = @JoinColumn(name = "id_game"),
          inverseJoinColumns = @JoinColumn(name = "id_platform"))
  private List<Platform> gameOn;


}
