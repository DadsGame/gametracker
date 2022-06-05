package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.UserGame;

import java.util.List;
import java.util.Map;

public interface UserGameService {

    UserGame save(UserGame userGame);

    List<Map<String, Object>> getLibrary(int userId);

    List<Map<String, Object>> getGlobalLibrary();
    List<Map<String, Object>> getUserLibrary(int userId);

    List<Map<String, Object>> checkIfPresentInLibrary(int userId, int gameId);

    UserGame update(UserGame userGame);
}
