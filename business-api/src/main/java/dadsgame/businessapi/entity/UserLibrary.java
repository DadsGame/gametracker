package dadsgame.businessapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user_library")
public class UserLibrary {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  @JsonIgnore
  @Column(name = "id_user")
  private long userLibrary;
  @OneToOne
  @JoinColumn(name = "id_game", referencedColumnName = "id")
  private Game game;
  @Column(name = "bought_at")
  private double boughtAt;
  @Column(name = "on_wishlist")
  private boolean onWishlist;
  @Column(name = "status")
  private String status;
  @Column(name = "created_at")
  private java.sql.Timestamp createdAt;
  @Column(name = "playtime")
  private long playtime;

}
