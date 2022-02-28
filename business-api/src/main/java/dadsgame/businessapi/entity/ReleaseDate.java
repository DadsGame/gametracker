package dadsgame.businessapi.entity;


public class ReleaseDate {

  private long id;
  private long idGameOn;
  private java.sql.Timestamp releasedAt;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getIdGameOn() {
    return idGameOn;
  }

  public void setIdGameOn(long idGameOn) {
    this.idGameOn = idGameOn;
  }


  public java.sql.Timestamp getReleasedAt() {
    return releasedAt;
  }

  public void setReleasedAt(java.sql.Timestamp releasedAt) {
    this.releasedAt = releasedAt;
  }

}
