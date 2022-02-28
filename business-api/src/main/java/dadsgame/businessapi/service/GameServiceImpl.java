package dadsgame.businessapi.service;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    GameRepository gameRepository;

    @Override
    public List<Game> getAllGame() {
        return gameRepository.findAll();
    }
}
