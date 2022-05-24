package dadsgame.businessapi.service.userService;

import dadsgame.businessapi.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> getAllUser();

    Optional<User> getUserById(int idUser);

    User createUser(User user);

    User update(User user);
}
