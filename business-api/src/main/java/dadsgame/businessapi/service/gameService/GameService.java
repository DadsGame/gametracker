package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.Game;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GameService {
    List<Game> getAllGame();

    Optional<Game> getGameById(int idGame);

    Game findGameByName(String gameName);
    Game findGameByIgdbId(String igdbId);
    Game save(Game game);

    List<Map<String, Object>> getBestRate();

    List<Map<String, Object>> getMostFinished();
}
