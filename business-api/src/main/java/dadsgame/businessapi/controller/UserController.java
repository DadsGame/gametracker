package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.User;
import dadsgame.businessapi.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUser(); }

    @GetMapping("/{idUser}")
    public Optional<User> getUserById(@PathVariable int idUser) { return userService.getUserById(idUser); }

    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        try {
            return new ResponseEntity<User>(userService.update(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
