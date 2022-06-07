package dadsgame.businessapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dadsgame.businessapi.controller.CommentController;
import dadsgame.businessapi.entity.Comment;
import dadsgame.businessapi.entity.GameTopic;
import dadsgame.businessapi.entity.Post;
import dadsgame.businessapi.exception.InvalidRequestException;
import dadsgame.businessapi.exception.NotFoundException;
import dadsgame.businessapi.service.forumService.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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

@WebMvcTest(CommentController.class)
@AutoConfigureMockMvc(addFilters = false)
class CommentControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	CommentService commentService;


	GameTopic GAMETOPIC_1 = new GameTopic(20, "Monster Hunter: Rise");
	Post POST_1 = Post.builder()
			.id(1)
			.author("Galimede")
			.title("Where do I find this object?")
			.content("I don't know where to find this object in this area, can you help plz ?")
			.gameTopic(GAMETOPIC_1.getId())
			.build();

	Comment COMMENT_1 = Comment.builder()
			.id(1)
			.author("Anonymous")
			.content("You can find it in the no zone.")
			.postComment(POST_1.getId())
			.build();
	GameTopic GAMETOPIC_2 = new GameTopic(10, "Overwatch 2");
	Post POST_2 = Post.builder()
			.id(1)
			.author("Tomota")
			.title("How do you quick scope?")
			.content("Everything is in the title")
			.gameTopic(GAMETOPIC_2.getId())
			.build();

	Comment COMMENT_2 = Comment.builder()
			.id(2)
			.author("John Doe")
			.content("Just press right mouse click mate.")
			.postComment(POST_2.getId())
			.build();

	GameTopic GAMETOPIC_3 = new GameTopic(1, "Counter Strike");
	Post POST_3 = Post.builder()
			.id(3)
			.author("Tuturo")
			.title("Which side is better on Dust 2?")
			.content("Imo, I do think CT is bettah.")
			.gameTopic(GAMETOPIC_3.getId())
			.build();

	Comment COMMENT_3 = Comment.builder()
			.id(3)
			.author("Tomota Michel")
			.content("I think you're right.")
			.postComment(POST_2.getId())
			.build();

	@Test
	public void getAllComments_success() throws Exception {
		List<Comment> commentList = new ArrayList<Comment>(Arrays.asList(COMMENT_1, COMMENT_2, COMMENT_3));

		Mockito.when(commentService.getAllComment()).thenReturn(commentList);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/comments")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[1].author", is("John Doe")))
				.andExpect(jsonPath("$[1].content", is("Just press right mouse click mate.")))
				.andExpect(jsonPath("$[1].postComment", is(1)));
	}

	@Test
	public void createComment_success() throws Exception {
		Comment OTHER_COMMENT = Comment.builder()
				.author("John Doe")
				.content("Another comment.")
				.postComment(POST_1.getId())
				.build();

		Mockito.when(commentService.save(OTHER_COMMENT)).thenReturn(OTHER_COMMENT);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/comments")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(OTHER_COMMENT));


		mockMvc.perform(mockRequest).andExpect(status().isOk());
	}

	@Test
	public void updateComment_success() throws Exception {
		Comment updatedComment = Comment.builder()
				.id(1)
				.author("Galimede")
				.content("Look I've updated the comment.")
				.build();

		Mockito.when(commentService.findById(COMMENT_1.getId())).thenReturn(Optional.of(COMMENT_1));
		Mockito.when(commentService.save(updatedComment)).thenReturn(updatedComment);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/comments")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updatedComment));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());


		List<Comment> commentList = new ArrayList<Comment>(Arrays.asList(updatedComment, COMMENT_2, COMMENT_3 ));

		Mockito.when(commentService.getAllComment()).thenReturn(commentList);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/comments")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0]", notNullValue()))
				.andExpect(jsonPath("$[0].author", is("Galimede")))
				.andExpect(jsonPath("$[0].content", is("Look I've updated the comment.")));
	}

	@Test
	public void updateComment_nullId() throws Exception {
		Comment updatedComment = Comment.builder()
				.author("Galimede")
				.content("Look I've updated the comment.")
				.build();

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/comments")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updatedComment));

		mockMvc.perform(mockRequest)
				.andExpect(status().isBadRequest())
				.andExpect(result ->
						assertTrue(result.getResolvedException() instanceof InvalidRequestException))
				.andExpect(result ->
						assertEquals("Comment or ID must not be null!", Objects.requireNonNull(result.getResolvedException()).getMessage()));
	}

	@Test
	public void updateComment_recordNotFound() throws Exception {
		Comment updatedComment = Comment.builder()
				.id(50)
				.author("Galimede")
				.content("Look I've updated the comment.")
				.build();

		Mockito.when(commentService.findById(updatedComment.getId())).thenReturn(null);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/comments")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updatedComment));

		mockMvc.perform(mockRequest)
				.andExpect(status().isBadRequest())
				.andExpect(result ->
						assertTrue(result.getResolvedException() instanceof NotFoundException))
				.andExpect(result ->
						assertEquals("Comment with ID 50 does not exist.", Objects.requireNonNull(result.getResolvedException()).getMessage()));
	}

	@Test
	public void deleteComment_success() throws Exception {
		Mockito.when(commentService.findById(COMMENT_1.getId())).thenReturn(Optional.of(COMMENT_1));

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/comments/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteComment_notFound() throws Exception {
		Mockito.when(commentService.findById(10)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/comments/10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(result ->
						assertTrue(result.getResolvedException() instanceof NotFoundException))
				.andExpect(result ->
						assertEquals("Comment with ID 10 does not exist.", Objects.requireNonNull(result.getResolvedException()).getMessage()));
	}



}
