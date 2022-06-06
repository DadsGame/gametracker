package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserGameRepository extends JpaRepository<UserGame, Integer> {

    @Query(value = "select id_user, id_game, bought_at, sold_at, status, platform, playtime, name, igdb_id from user_game join game g on user_game.id_game = g.id where user_game.id_user = ?1", nativeQuery = true)
    List<Map<String, Object>> findByUserLibrary(int userLibrary);

    @Query(value = "select  status as most_present_status,\n" +
            "       (select avg(bought_at) from user_game) as avg_spent,\n" +
            "       (select avg(sold_at) from user_game) as avg_revenue,\n" +
            "       (select username from \"user\" where id = (select id_user from user_game as id_most_user group by id_user order by count(id_user) desc limit 1)) as user_with_max_games,\n" +
            "       (select sum(bought_at) from user_game) as total_spent_players,\n" +
            "       (select sum(sold_at) from user_game) as total_revenue_players\n" +
            "from user_game group by status order by count(*) desc limit 1;", nativeQuery = true)
    List<Map<String, Object>> getStatsLibraryGlobal();
    @Query(value = "select  status as most_present_status,\n" +
            "       (select count(*) from user_game where id_user = ?1) as total_game,\n" +
            "       (select avg(bought_at) from user_game where id_user = ?1) as avg_spent,\n" +
            "       (select avg(sold_at) from user_game where id_user = ?1) as avg_revenue,\n" +
            "       (select sum(bought_at) from user_game where id_user = ?1) as total_spent_player,\n" +
            "       (select sum(sold_at) from user_game where id_user = ?1) as total_revenue_player\n" +
            " from user_game where id_user = ?1 group by status order by count(*) desc limit 1;", nativeQuery = true)
    List<Map<String, Object>> getStatsLibraryUser(int userId);

    @Query(value = "select count(*) from user_game where id_user = ?1 and id_game = ?2", nativeQuery = true)
    List<Map<String, Object>> checkIfPresentInLibrary(int userId, int gameId);

    UserGame findByIdGameEqualsAndUserLibraryEquals(int idGame, int userLibrary);

}
