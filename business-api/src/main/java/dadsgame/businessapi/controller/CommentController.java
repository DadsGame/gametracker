package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.Comment;
import dadsgame.businessapi.exception.InvalidRequestException;
import dadsgame.businessapi.exception.NotFoundException;
import dadsgame.businessapi.service.forumService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> getAllComment() {
        return commentService.getAllComment();
    }

    @PostMapping
    public Comment createComment(@RequestBody @Valid Comment comment) {
        return commentService.save(comment);
    }
    @PostMapping("/currentUser")
    public Comment createCommentCurrent(@RequestBody @Valid Comment comment) {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setAuthor(username);
        return commentService.save(comment);
    }

    @PutMapping
    public Comment updateComment(@RequestBody Comment post) throws NotFoundException {
        if(post == null || post.getId() == 0) {
            throw new InvalidRequestException("Comment or ID must not be null!");
        }

        Optional<Comment> optionalComment = commentService.findById(post.getId());
        if(optionalComment == null || optionalComment.isEmpty()) {
            throw new NotFoundException("Comment with ID " + post.getId() + " does not exist.");
        }

        Comment existingComment = optionalComment.get();

        existingComment.setAuthor(post.getAuthor());
        existingComment.setContent(post.getContent());

        return commentService.save(existingComment);
    }

    @DeleteMapping(value = "{commentId}")
    public void deleteCommentById(@PathVariable(value = "commentId") int commentId) throws NotFoundException {
        Optional<Comment> optionalComment = commentService.findById(commentId);

        if (optionalComment == null || optionalComment.isEmpty()) {
            throw new NotFoundException("Comment with ID " + commentId + " does not exist.");
        }

        commentService.deleteById(commentId);
    }



}
