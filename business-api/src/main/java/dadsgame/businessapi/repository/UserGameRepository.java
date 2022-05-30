package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.UserGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGameRepository extends JpaRepository<UserGame, Integer> {

//    @Query(" select u from UserGame u " +
//            " where u.userLibrary = ?1")
//    List<UserGame> getLibrary(int userId);

    List<UserGame> findByUserLibraryEquals(int userLibrary);


}
