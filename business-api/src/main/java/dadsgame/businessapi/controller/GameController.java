package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.*;
import dadsgame.businessapi.service.gameService.GameService;
import dadsgame.businessapi.service.gameService.UserGameService;
import dadsgame.businessapi.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/games")
public class GameController {

    @Autowired
    GameService gameService;
    @Autowired
    UserGameService userGameService;
    @Autowired
    UserService userService;


    @GetMapping
    public List<Game> getGames() {
        return gameService.getAllGame();
    }

    @GetMapping("/{idGame}")
    public Optional<Game> getGameById(@PathVariable int idGame) { return gameService.getGameById(idGame); }

    @GetMapping("/byGameName")
    public ResponseEntity getGameByName(@RequestParam("name") String gameName) {
        Game g = gameService.findGameByName(gameName);
        if(g == null ||gameName == null|| gameName.isEmpty()) {
        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        }
        return new ResponseEntity<>(g, HttpStatus.OK);
    }
    @GetMapping("/byIgdb")
    public ResponseEntity getGameByIgdbId(@RequestParam("id") String igdbId) {
        Game g = gameService.findGameByIgdbId(igdbId);
        if(g == null || igdbId == null || igdbId.isEmpty()) {
            return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
        }
        return new ResponseEntity<>(g, HttpStatus.OK);
    }

    @PostMapping
    public Game postGame(@RequestBody @Valid Game game) {
        return gameService.save(game);
    }

    @PostMapping("/toLibrary")
    public UserGame postGameToLibrary(@RequestBody @Valid UserGame userGame) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        userGame.setUserLibrary(userId);
        return userGameService.save(userGame);
    }

    @GetMapping("/userLibrary")
    public List<Map<String, Object>> getLibraryFromUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        List<Map<String, Object>> userGameList =  userGameService.getLibrary(userId);
        if(userGameList == null || userGameList.isEmpty()) return List.of();
        return userGameList;
    }
    @GetMapping("/userLibrary/isPresent/{gameId}")
    public List<Map<String, Object>> getGamePresentInLibrary(@PathVariable int gameId) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        List<Map<String, Object>> userGameList =  userGameService.checkIfPresentInLibrary(userId, gameId);
        if(userGameList == null || userGameList.isEmpty()) return List.of();
        return userGameList;
    }

    @PutMapping("/userLibrary")
    public ResponseEntity<UserGame> updateUser(@RequestBody UserGame userGame){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        userGame.setUserLibrary(userId);
        try {
            return new ResponseEntity<UserGame>(userGameService.update(userGame), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stats/global")
    public List<Map<String, Object>> getGlobalStats() {
        return userGameService.getGlobalLibrary();
    }

    @GetMapping("/stats/user")
    public List<Map<String, Object>> getUserStats() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        return userGameService.getUserLibrary(userId);
    }

    @PostMapping("/addReview")
    public GameReview postGrameReview(@RequestBody @Valid GameReview gameReview) {
        return userGameService.addReview(gameReview);
    }

    @GetMapping("review")
    public List<Map<String, Object>> getGameReview(@RequestParam("idGame") Integer gameId) {
        List<Map<String, Object>> gameReviewList = userGameService.getReviewByGame(gameId);
        return gameReviewList;
    }
    @GetMapping("reviewIgdb")
    public List<Map<String, Object>> getGameReviewByIgdb(@RequestParam("idGame") String gameId) {
        List<Map<String, Object>> gameReviewList = userGameService.getReviewByGameIgdb(gameId);
        return gameReviewList;
    }

    @GetMapping("bestRating")
    public List<Map<String, Object>> getBestRating() {
        List<Map<String, Object>> gameBestRateList = gameService.getBestRate();
        return gameBestRateList;
    }

    @GetMapping("{idGame}/isWished")
    public boolean isWishedByUser(@PathVariable int idGame){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        Game g = gameService.findGameByIgdbId(String.valueOf(idGame));
        return userGameService.isWishedByUser(userId, g.getId());
    }

    @PostMapping("addToWishlist/{idGame}")
    public UserWishlist postWishList(@PathVariable int idGame){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        Game g = gameService.findGameByIgdbId(String.valueOf(idGame));
        UserWishlist uw = new UserWishlist(userId, g.getId());
        return userGameService.postGameToWishList(uw);
    }
    @DeleteMapping("/userWishlist/{idGame}")
    public Integer deleteWish(@PathVariable int idGame){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        Game g = gameService.findGameByIgdbId(String.valueOf(idGame));
        return userGameService.deleteWish(userId, g.getId());
    }

    @GetMapping("/wishlist")
    public List<Map<String, Object>> getWishList(){
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userLogged = userService.findByUserName(username);
        int userId = userLogged.getId();
        return userGameService.getWishList(userId);
    }

    @GetMapping("/mostFinished")
    public List<Map<String, Object>> getMostFinished() {
        return gameService.getMostFinished();
    }

    @GetMapping("/averagePlaytime")
    public List<Map<String, Object>> getAveragePlaytime() {
        return gameService.getAveragePlaytime();
    }

}
