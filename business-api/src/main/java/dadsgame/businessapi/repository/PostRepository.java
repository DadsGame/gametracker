package dadsgame.businessapi.repository;

import dadsgame.businessapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * from post where id_gametopic=?1", nativeQuery = true)
    List<Post> getPostsByGameTopic(int gameTopic);

    @Query(value = "select * from post where author=?1", nativeQuery = true)
    List<Post> getAuthorPosts(String author);
}
