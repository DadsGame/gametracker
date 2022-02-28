package dadsgame.businessapi.entity;

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
@Table
public class UserLibrary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private long idUser;
  private long idGame;
  private double boughtAt;
  private boolean onWishilist;
  private String status;
  private java.sql.Timestamp createdAt;
  private long playtime;

}
