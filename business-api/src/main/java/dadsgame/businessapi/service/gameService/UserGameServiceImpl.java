package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.entity.UserGame;
import dadsgame.businessapi.repository.GameRepository;
import dadsgame.businessapi.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGameServiceImpl implements UserGameService {
    @Autowired
    UserGameRepository userGameRepository;


    @Override
    public UserGame save(UserGame userGame) {
        return userGameRepository.save(userGame);
    }

    @Override
    public List<UserGame> getLibrary(int userId) {
        return userGameRepository.findByUserLibraryEquals(userId);
    }
}
