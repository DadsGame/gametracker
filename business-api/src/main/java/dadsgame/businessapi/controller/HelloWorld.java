package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.Game;
import dadsgame.businessapi.entity.User;
import dadsgame.businessapi.service.gameService.GameService;
import dadsgame.businessapi.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorld {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @GetMapping("/games")
    public List<Game> getGames() {
        return gameService.getAllGame();
    }

    @GetMapping("/users")
    public List<User> getUsers() { return userService.getAllUser(); }

}
