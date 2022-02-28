package dadsgame.businessapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "game_type")
public class GameType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private long idGame;
  private long idType;


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


  public long getIdType() {
    return idType;
  }

  public void setIdType(long idType) {
    this.idType = idType;
  }

}
