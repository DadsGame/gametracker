package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface GameRepository extends JpaRepository<Game, Integer> {

    @Query(" select u from Game u " +
            " where u.name = ?1")
    Game findGameByName(String gameName);
    @Query(" select u from Game u " +
            " where u.igdbId = ?1")
    Game findGameByIgdbId(String igdbId);

    @Query(value = "select g.id, igdb_id, name, t.avg as avg from (select id_game, AVG(rate) as avg from game_review as gr" +
            " group by id_game) t join game g on g.id = t.id_game order by t.avg DESC LIMIT 10",nativeQuery = true)
    List<Map<String, Object>> findByBestRate();

    @Query(value = "select g.id, g.igdb_id, name, t.sm as sm from (select id_game, COUNT(id_game) as sm from user_game where status='finished' group by id_game) t join game g on g.id = t.id_game order by t.sm DESC LIMIT 10",nativeQuery = true)
    List<Map<String, Object>> findByMostFinished();
}
