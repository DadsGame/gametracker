package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.entity.UserGame;
import dadsgame.businessapi.repository.GameRepository;
import dadsgame.businessapi.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public  List<Map<String, Object>> getLibrary(int userId) {
        return userGameRepository.findByUserLibrary(userId);
    }

    @Override
    public  List<Map<String, Object>> getGlobalLibrary() {
       return ( List<Map<String, Object>>) userGameRepository.getStatsLibraryGlobal();
    }

    @Override
    public List<Map<String, Object>> getUserLibrary(int userId) {
        return ( List<Map<String, Object>>) userGameRepository.getStatsLibraryUser(userId);
    }

    @Override
    public List<Map<String, Object>> checkIfPresentInLibrary(int userId, int gameId) {
        return userGameRepository.checkIfPresentInLibrary(userId, gameId);
    }

    @Override
    public UserGame update(UserGame userGame) {
        UserGame foundUserGame = userGameRepository.findByIdGameEqualsAndUserLibraryEquals(userGame.getIdGame(), userGame.getUserLibrary());

        foundUserGame.setPlaytime(userGame.getPlaytime());
        foundUserGame.setBoughtAt(userGame.getBoughtAt());
        foundUserGame.setSoldAt(userGame.getSoldAt());
        foundUserGame.setStatus(userGame.getStatus());

        return userGameRepository.save(foundUserGame);
    }
}
