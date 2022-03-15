package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGame();

    Optional<Game> getGameById(int idGame);
}
