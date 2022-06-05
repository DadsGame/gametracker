package dadsgame.businessapi.service.gameService;

import dadsgame.businessapi.entity.GameReview;
import dadsgame.businessapi.entity.UserGame;
import dadsgame.businessapi.repository.GameReviewRepository;
import dadsgame.businessapi.repository.UserGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserGameServiceImpl implements UserGameService {
    @Autowired
    UserGameRepository userGameRepository;
    @Autowired
    GameReviewRepository gameReviewRepository;


    @Override
    public UserGame save(UserGame userGame) {
        UserGame ug = userGameRepository.findByIdGameEqualsAndUserLibraryEquals(userGame.getIdGame(), userGame.getUserLibrary());
        if (ug != null) {
            ug.setPlaytime(userGame.getPlaytime());
            ug.setBoughtAt(userGame.getBoughtAt());
            ug.setSoldAt(userGame.getSoldAt());
            ug.setStatus(userGame.getStatus());
            return userGameRepository.save(ug);
        }
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

    @Override
    public GameReview addReview(GameReview gameReview) {
        Optional<GameReview> review = gameReviewRepository.findByIdUserAndIdGame(gameReview.getIdUser(), gameReview.getIdGame());
        if (review.isPresent()) {
            review.get().setRate(gameReview.getRate());
            review.get().setReview(gameReview.getReview());
            return gameReviewRepository.save(review.get());
        }
        return gameReviewRepository.save(gameReview);
    }

    @Override
    public List<Map<String, Object>> getReviewByGame(Integer gameId) {
        return gameReviewRepository.findAllReviewByGameId(gameId);
    }
}
