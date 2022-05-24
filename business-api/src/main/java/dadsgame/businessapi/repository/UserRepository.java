package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(" select u from UserEntity u " +
            " where u.username = ?1")
    UserEntity findUserWithName(String username);

}
