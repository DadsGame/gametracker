package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(" select u from Game u " +
            " where u.name = ?1")
    Game findGameByName(String gameName);
    @Query(" select u from Game u " +
            " where u.igdbId = ?1")
    Game findGameByIgdbId(String igdbId);
}
