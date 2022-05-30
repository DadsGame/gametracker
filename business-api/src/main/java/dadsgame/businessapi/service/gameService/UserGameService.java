package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.UserGame;

import java.util.List;

public interface UserGameService {

    UserGame save(UserGame userGame);

    List<UserGame> getLibrary(int userId);
}
