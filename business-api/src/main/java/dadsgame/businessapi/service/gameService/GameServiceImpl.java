package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    @Override
    public Game findGameByName(String gameName) {
        return gameRepository.findGameByName(gameName);
    }

    @Override
    public Game findGameByIgdbId(String igdbId) {
        return gameRepository.findGameByIgdbId(igdbId);
    }
    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public List<Map<String, Object>> getBestRate() {
        return gameRepository.findByBestRate();
    }

    @Override
    public List<Map<String, Object>> getMostFinished() {
        return gameRepository.findByMostFinished();
    }
}
