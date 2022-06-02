package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GameReviewRepository extends JpaRepository<GameReview, Integer> {
    Optional<GameReview> findByIdUserAndIdGame(int idUser, int idGame);

    @Query(value = "select username, rate, review from game_review g join \"user\" u on g.id_user = u.id where g.id_game = ?1", nativeQuery = true)
    List<Map<String, Object>> findAllReviewByGameId(Integer gameId);
}
