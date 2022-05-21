package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.Post;
import dadsgame.businessapi.exception.InvalidRequestException;
import dadsgame.businessapi.exception.NotFoundException;
import dadsgame.businessapi.service.forumService.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping("/byGameTopic/{idGameTopic}")
    public List<Post> getPostByGameTopic(@PathVariable int idGameTopic) {
        return postService.getPostsByGameTopic(idGameTopic);
    }

    @GetMapping("/byAuthor/{author}")
    public List<Post> getAuthorPosts(@PathVariable String author) {
        return postService.getAuthorPosts(author);
    }

    @PostMapping
    public Post createPost(@RequestBody @Valid Post post) {
        return postService.save(post);
    }

    @PutMapping
    public Post updatePost(@RequestBody Post post) throws NotFoundException {
        if(post == null || post.getId() == 0) {
            throw new InvalidRequestException("Post or ID must not be null!");
        }

        Optional<Post> optionalPost = postService.findById(post.getId());
        if(optionalPost == null || optionalPost.isEmpty()) {
            throw new NotFoundException("Post with ID " + post.getId() + " does not exist.");
        }

        Post existingPost = optionalPost.get();

        existingPost.setAuthor(post.getAuthor());
        existingPost.setContent(post.getContent());
        existingPost.setTitle(post.getTitle());

        return postService.save(existingPost);
    }

    @DeleteMapping(value = "{postId}")
    public void deletePostById(@PathVariable(value = "postId") int postId) throws NotFoundException {
        Optional<Post> optionalPost = postService.findById(postId);

        if (optionalPost == null || optionalPost.isEmpty()) {
            throw new NotFoundException("Post with ID " + postId + " does not exist.");
        }

        postService.deleteById(postId);
    }
}
