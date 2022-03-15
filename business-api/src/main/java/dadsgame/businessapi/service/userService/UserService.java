package dadsgame.businessapi.service.userService;

import dadsgame.businessapi.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUser();

    Optional<User> getUserById(int idUser);

    User createUser(User user);

    User update(User user);
}
