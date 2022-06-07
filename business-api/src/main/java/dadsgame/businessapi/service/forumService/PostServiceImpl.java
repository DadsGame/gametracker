package dadsgame.businessapi.service.forumService;

import dadsgame.businessapi.entity.Post;
import dadsgame.businessapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByGameTopic(int gameTopic) {
        return postRepository.getPostsByGameTopic(gameTopic);
    }

    @Override
    public List<Post> getPostsByGameTopicFiltered(int gameTopic) {
        return postRepository.getPostsByGameTopicFiltered(gameTopic);
    }

    @Override
    public List<Post> getAuthorPosts(String author) {
        return postRepository.getAuthorPosts(author);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> findById(int postId) {
        return postRepository.findById(postId);
    }

    @Override
    public void deleteById(int postId) {
        postRepository.deleteById(postId);
    }
}
