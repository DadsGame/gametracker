package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
