package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.GameReview;
import dadsgame.businessapi.entity.UserGame;

import java.util.List;
import java.util.Map;

public interface UserGameService {

    UserGame save(UserGame userGame);

    List<Map<String, Object>> getLibrary(int userId);

    List<Map<String, Object>> getGlobalLibrary();
    List<Map<String, Object>> getUserLibrary(int userId);

    GameReview addReview(GameReview gameReview);


    List<Map<String, Object>> getReviewByGame(Integer gameId);
}
