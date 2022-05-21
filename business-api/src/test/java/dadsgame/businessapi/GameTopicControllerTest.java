package dadsgame.businessapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import dadsgame.businessapi.controller.GameTopicController;
import dadsgame.businessapi.entity.GameTopic;
import dadsgame.businessapi.service.forumService.GameTopicService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameTopicController.class)
class GameTopicControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	GameTopicService gameTopicService;

	GameTopic GAMETOPIC_1 = new GameTopic(1, "Counter Strike");
	GameTopic GAMETOPIC_2 = new GameTopic(10, "Overwatch 2");
	GameTopic GAMETOPIC_3 = new GameTopic(20, "Monster Hunter: Rise");

	@Test
	public void getAllGameTopic_success() throws Exception {
		List<GameTopic> gameTopicList = new ArrayList<GameTopic>(Arrays.asList(GAMETOPIC_1, GAMETOPIC_2, GAMETOPIC_3));

		Mockito.when(gameTopicService.getAllGameTopic()).thenReturn(gameTopicList);

		mockMvc.perform(MockMvcRequestBuilders
				.get("/gametopics")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[1].name", is("Overwatch 2")));
	}

	@Test
	public void getGameTopicById_success() throws Exception {
		Mockito.when(gameTopicService.getGameTopicById(GAMETOPIC_1.getId())).thenReturn(java.util.Optional.of(GAMETOPIC_1));

		mockMvc.perform(MockMvcRequestBuilders
						.get("/gametopics/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Counter Strike")));
	}

	@Test
	public void createGameTopic_success() throws Exception {
		GameTopic gameTopic = new GameTopic(42, "Planet Coaster");

		Mockito.when(gameTopicService.save(gameTopic)).thenReturn(gameTopic);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/gametopics")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(gameTopic));


		mockMvc.perform(mockRequest).andExpect(status().isOk());
	}

}
