package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.GameReview;
import dadsgame.businessapi.entity.UserGame;
import dadsgame.businessapi.entity.UserWishlist;

import java.util.List;
import java.util.Map;

public interface UserGameService {

    UserGame save(UserGame userGame);

    List<Map<String, Object>> getLibrary(int userId);

    List<Map<String, Object>> getGlobalLibrary();
    List<Map<String, Object>> getUserLibrary(int userId);

    List<Map<String, Object>> checkIfPresentInLibrary(int userId, int gameId);
    GameReview addReview(GameReview gameReview);

    UserGame update(UserGame userGame);

    List<Map<String, Object>> getReviewByGame(Integer gameId);

    List<Map<String, Object>> getReviewByGameIgdb(String gameId);

    boolean isWishedByUser(int userId, int idGame);

    UserWishlist postGameToWishList(UserWishlist userWishlist);

    Integer deleteWish(int userId, int idGame);

    List<Map<String, Object>> getWishList(int userId);
}
