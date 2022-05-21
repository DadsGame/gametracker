package dadsgame.businessapi.service.forumService;

import dadsgame.businessapi.entity.Comment;
import dadsgame.businessapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;


    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> findById(int commentId) {
        return commentRepository.findById(commentId);
    }

    @Override
    public void deleteById(int commentId) {
        commentRepository.deleteById(commentId);
    }
}
