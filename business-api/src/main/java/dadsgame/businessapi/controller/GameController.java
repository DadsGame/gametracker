package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.service.gameService.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    @Autowired
    GameService gameService;


    @GetMapping
    public List<Game> getGames() {
        return gameService.getAllGame();
    }

    @GetMapping("/{idGame}")
    public Optional<Game> getGameById(@PathVariable int idGame) { return gameService.getGameById(idGame); }

}
