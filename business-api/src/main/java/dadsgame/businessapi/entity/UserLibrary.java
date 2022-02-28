package dadsgame.businessapi.entity;


public class UserLibrary {

  private long id;
  private long idUser;
  private long idGame;
  private double boughtAt;
  private boolean onWishilist;
  private String status;
  private java.sql.Timestamp createdAt;
  private long playtime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIdUser() {
    return idUser;
  }

  public void setIdUser(long idUser) {
    this.idUser = idUser;
  }


  public long getIdGame() {
    return idGame;
  }

  public void setIdGame(long idGame) {
    this.idGame = idGame;
  }


  public double getBoughtAt() {
    return boughtAt;
  }

  public void setBoughtAt(double boughtAt) {
    this.boughtAt = boughtAt;
  }


  public boolean isOnWishilist() {
    return onWishilist;
  }

  public void setOnWishilist(boolean onWishilist) {
    this.onWishilist = onWishilist;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }


  public long getPlaytime() {
    return playtime;
  }

  public void setPlaytime(long playtime) {
    this.playtime = playtime;
  }

}
