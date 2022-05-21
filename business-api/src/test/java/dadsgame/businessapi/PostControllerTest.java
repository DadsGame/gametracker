package dadsgame.businessapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dadsgame.businessapi.controller.PostController;
import dadsgame.businessapi.entity.GameTopic;
import dadsgame.businessapi.entity.Post;
import dadsgame.businessapi.exception.InvalidRequestException;
import dadsgame.businessapi.exception.NotFoundException;
import dadsgame.businessapi.service.forumService.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    PostService postService;


    GameTopic GAMETOPIC_1 = new GameTopic(20, "Monster Hunter: Rise");
    Post POST_1 = Post.builder()
            .id(1)
            .author("Galimede")
            .title("Where do I find this object?")
            .content("I don't know where to find this object in this area, can you help plz ?")
            .gameTopic(GAMETOPIC_1.getId())
            .build();
    GameTopic GAMETOPIC_2 = new GameTopic(10, "Overwatch 2");
    Post POST_2 = Post.builder()
            .id(2)
            .author("Tomota")
            .title("How do you quick scope?")
            .content("Everything is in the title")
            .gameTopic(GAMETOPIC_2.getId())
            .build();

    GameTopic GAMETOPIC_3 = new GameTopic(1, "Counter Strike");
    Post POST_3 = Post.builder()
            .id(3)
            .author("Tuturo")
            .title("Which side is better on Dust 2?")
            .content("Imo, I do think CT is bettah.")
            .gameTopic(GAMETOPIC_3.getId())
            .build();

    @Test
    public void getAllPost_success() throws Exception {
        List<Post> postList = new ArrayList<Post>(Arrays.asList(POST_1, POST_2, POST_3));

        Mockito.when(postService.getAllPost()).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].author", is("Tomota")))
                .andExpect(jsonPath("$[1].title", is("How do you quick scope?")))
                .andExpect(jsonPath("$[1].content", is("Everything is in the title")))
                .andExpect(jsonPath("$[1].gameTopic", is(10)));
    }

    @Test
    public void getPostsByGameTopic_success() throws Exception {

        Post OTHER_POST = Post.builder()
                .author("John Doe")
                .title("My map is broken?")
                .content("I can't find the monster to hunt on my mini map is there a bug?")
                .gameTopic(GAMETOPIC_1.getId())
                .build();

        List<Post> postList = new ArrayList<Post>(Arrays.asList(POST_1, OTHER_POST));

        Mockito.when(postService.getPostsByGameTopic(GAMETOPIC_1.getId())).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts/byGameTopic/20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].author", is("Galimede")))
                .andExpect(jsonPath("$[0].title", is("Where do I find this object?")))
                .andExpect(jsonPath("$[0].content", is("I don't know where to find this object in this area, can you help plz ?")))
                .andExpect(jsonPath("$[0].gameTopic", is(20)));
    }

    @Test
    public void getAuthorPosts_success() throws Exception {
        Post OTHER_POST = Post.builder()
                .author("Galimede")
                .title("What's the best strategy")
                .content("I'm new to the game plz help.")
                .gameTopic(GAMETOPIC_1.getId())
                .build();

        List<Post> postList = new ArrayList<Post>(Arrays.asList(POST_1, OTHER_POST));

        Mockito.when(postService.getAuthorPosts(POST_1.getAuthor())).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts/byAuthor/Galimede")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].author", is("Galimede")))
                .andExpect(jsonPath("$[0].title", is("Where do I find this object?")))
                .andExpect(jsonPath("$[0].content", is("I don't know where to find this object in this area, can you help plz ?")))
                .andExpect(jsonPath("$[0].gameTopic", is(20)));
    }

    @Test
    public void createPost_success() throws Exception {
        Post OTHER_POST = Post.builder()
                .author("Galimede")
                .title("What's the best strategy")
                .content("I'm new to the game plz help.")
                .gameTopic(GAMETOPIC_1.getId())
                .build();

        Mockito.when(postService.save(OTHER_POST)).thenReturn(OTHER_POST);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(OTHER_POST));


        mockMvc.perform(mockRequest).andExpect(status().isOk());
    }

    @Test
    public void updatePost_success() throws Exception {
        Post updatedPost = Post.builder()
                .id(3)
                .author("Galimede")
                .title("What's the best strategy")
                .content("I'm new to the game plz help.")
                .build();

        Mockito.when(postService.findById(POST_3.getId())).thenReturn(Optional.of(POST_1));
        Mockito.when(postService.save(updatedPost)).thenReturn(updatedPost);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedPost));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());


        List<Post> postList = new ArrayList<Post>(Arrays.asList(updatedPost));

        Mockito.when(postService.getPostsByGameTopic(GAMETOPIC_3.getId())).thenReturn(postList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/posts/byGameTopic/" + GAMETOPIC_3.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0]", notNullValue()))
                .andExpect(jsonPath("$[0].author", is("Galimede")))
                .andExpect(jsonPath("$[0].title", is("What's the best strategy")))
                .andExpect(jsonPath("$[0].content", is("I'm new to the game plz help.")));
    }

    @Test
    public void updatePost_nullId() throws Exception {
        Post updatedPost = Post.builder()
                .author("Johnny Doe")
                .title("Twenty gems")
                .content("Where do I find them")
                .build();

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedPost));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof InvalidRequestException))
                .andExpect(result ->
                        assertEquals("Post or ID must not be null!", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }

    @Test
    public void updatePost_recordNotFound() throws Exception {
        Post updatedPost = Post.builder()
                .id(42)
                .author("Johnny Doe")
                .title("Twenty gems")
                .content("Where do I find them")
                .build();

        Mockito.when(postService.findById(updatedPost.getId())).thenReturn(null);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updatedPost));

        mockMvc.perform(mockRequest)
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result ->
                        assertEquals("Post with ID 42 does not exist.", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }


    @Test
    public void deletePost_success() throws Exception {
        Mockito.when(postService.findById(POST_2.getId())).thenReturn(Optional.of(POST_2));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/posts/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePost_notFound() throws Exception {
        Mockito.when(postService.findById(10)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/posts/10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof NotFoundException))
                .andExpect(result ->
                        assertEquals("Post with ID 10 does not exist.", Objects.requireNonNull(result.getResolvedException()).getMessage()));
    }


}
