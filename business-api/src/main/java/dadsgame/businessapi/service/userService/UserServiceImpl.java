package dadsgame.businessapi.service.userService;

import dadsgame.businessapi.entity.User;
import dadsgame.businessapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public boolean ifOwned(User user){

    }
}