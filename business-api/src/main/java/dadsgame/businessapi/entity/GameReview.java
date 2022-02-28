package dadsgame.businessapi.entity;


public class GameReview {

  private long id;
  private long idUser;
  private long idGame;
  private long rate;
  private String review;


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


  public long getRate() {
    return rate;
  }

  public void setRate(long rate) {
    this.rate = rate;
  }


  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

}
