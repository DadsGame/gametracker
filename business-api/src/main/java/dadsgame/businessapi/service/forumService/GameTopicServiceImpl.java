package dadsgame.businessapi.service.forumService;

import dadsgame.businessapi.entity.GameTopic;
import dadsgame.businessapi.repository.GameTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameTopicServiceImpl implements GameTopicService {

    @Autowired
    GameTopicRepository gameTopicRepository;

    @Override
    public List<GameTopic> getAllGameTopic() {
        return gameTopicRepository.findAll();
    }

    @Override
    public Optional<GameTopic> getGameTopicById(int idGame) {
        return gameTopicRepository.findById(idGame);
    }

    @Override
    public GameTopic save(GameTopic gameTopic) {
        return gameTopicRepository.save(gameTopic);
    }
}
