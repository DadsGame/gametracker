package dadsgame.businessapi.entity;
import javax.persistence.*;

@Entity
@Table(name = "game_on")
public class GameOn {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private long idGame;
  private long idPlatform;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIdGame() {
    return idGame;
  }

  public void setIdGame(long idGame) {
    this.idGame = idGame;
  }


  public long getIdPlatform() {
    return idPlatform;
  }

  public void setIdPlatform(long idPlatform) {
    this.idPlatform = idPlatform;
  }

}
