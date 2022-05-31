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
@Table(name = "user_game")
public class UserGame {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private int id;
  @JsonIgnore
  @Column(name = "id_user")
  private int userLibrary;
  @Column(name = "id_game")
  private int idGame;
  @Column(name = "bought_at")
  private double boughtAt;
  @Column(name = "sold_at")
  private double soldAt;
  @Column(name = "status")
  private String status;
  @Column(name = "created_at")
  private java.sql.Timestamp createdAt;
  @Column(name = "playtime")
  private long playtime;

}
