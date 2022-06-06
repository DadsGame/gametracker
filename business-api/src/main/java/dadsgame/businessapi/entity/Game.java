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
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "publisher")
  private String publisher;
  @Column(name = "developer")
  private String developer;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private double price;
  @Column(name = "igdb_id")
  private String igdbId;
  @OneToMany(mappedBy = "gameRelease")
  private List<ReleaseDate> releaseDate;

}
