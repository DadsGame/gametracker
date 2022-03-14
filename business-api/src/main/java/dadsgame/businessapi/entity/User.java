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
@Table(name = "user", schema = "public")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  @Column(name = "name")
  private String name;
  @Column(name = "firstname")
  private String firstname;
  @Column(name = "email")
  private String email;
  @Column(name = "password")
  private String password;
  @Column(name = "username")
  private String username;
  @Column(name = "profile_picture")
  private String profilePicture;
  @OneToMany(mappedBy = "userLibrary")
  private List<UserLibrary> userLibrary;

}
