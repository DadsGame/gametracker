package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.ReleaseDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseDateRepository extends JpaRepository<ReleaseDate, Integer> {
}
