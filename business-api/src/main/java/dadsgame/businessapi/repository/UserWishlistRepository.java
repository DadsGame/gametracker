package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.UserWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserWishlistRepository extends JpaRepository<UserWishlist, Integer> {
    Optional<UserWishlist> findByIdUserAndIdGame(int userId, int idGame);

    Integer deleteByIdUserAndIdGame(int userId, int idGame);

    @Query(value = "select g.id, g.name, g.igdb_id from game g join user_wishlist u on g.id = u.id_game where u.id_user = ?1",nativeQuery = true)
    List<Map<String, Object>> getUserWishList(int userId);
}
