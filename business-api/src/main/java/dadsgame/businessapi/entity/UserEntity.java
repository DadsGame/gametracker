package dadsgame.businessapi.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user", schema = "public")
public class UserEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private int id;
  @Column(name = "email")
  private String email;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "profile_picture")
  private String profilePicture;
  @OneToMany(mappedBy = "userLibrary")
  private List<UserGame> userLibrary;


}
