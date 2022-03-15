package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;

    @Override
    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }

    @Override
    public Optional<Game> getGameById(int idGame) {
        return gameRepository.findById(idGame);
    }
}
