package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorld {

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public List<Game> index() {
        return gameService.getAllGame();
    }
}
