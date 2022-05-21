package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.GameTopic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTopicRepository extends JpaRepository<GameTopic, Integer> {
}
