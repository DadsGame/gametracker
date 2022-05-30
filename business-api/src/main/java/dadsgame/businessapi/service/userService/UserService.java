package dadsgame.businessapi.service.userService;

import dadsgame.businessapi.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<UserEntity> getAllUser();

    Optional<UserEntity> getUserById(int idUser);

    UserEntity createUser(UserEntity userEntity);

    UserEntity findByUserName(String username);

    UserEntity update(UserEntity userEntity);
}
