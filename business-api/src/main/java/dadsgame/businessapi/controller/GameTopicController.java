package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.GameTopic;
import dadsgame.businessapi.service.forumService.GameTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gametopics")
public class GameTopicController {

    @Autowired
    GameTopicService gameTopicService;

    @GetMapping
    public List<GameTopic> getGameTopics() {
        return gameTopicService.getAllGameTopic();
    }

    @GetMapping("/{idGame}")
    public Optional<GameTopic> getGameTopicById(@PathVariable int idGame) { return gameTopicService.getGameTopicById(idGame); }

    @PostMapping
    public GameTopic createGameTopic(@RequestBody @Valid GameTopic gameTopic) {
        return gameTopicService.save(gameTopic);
    }
}
