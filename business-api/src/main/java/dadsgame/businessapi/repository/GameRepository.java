package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {
}
