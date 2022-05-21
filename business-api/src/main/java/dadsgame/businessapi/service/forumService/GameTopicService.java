package dadsgame.businessapi.service.forumService;

import dadsgame.businessapi.entity.GameTopic;

import java.util.List;
import java.util.Optional;

public interface GameTopicService {

    List<GameTopic> getAllGameTopic();

    Optional<GameTopic> getGameTopicById(int idGame);

    GameTopic save(GameTopic gameTopic);
}
