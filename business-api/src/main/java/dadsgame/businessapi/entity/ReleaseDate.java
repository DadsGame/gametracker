package dadsgame.businessapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "release_date")
public class ReleaseDate {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private int id;
  @Column(name = "released_at")
  private java.sql.Timestamp releasedAt;

  @OneToOne
  @JoinColumn(name = "id_platform", referencedColumnName = "id")
  private Platform platform;

  @JsonIgnore
  @Column(name = "id_game")
  private int gameRelease;

}
