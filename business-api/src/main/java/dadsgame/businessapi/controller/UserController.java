package dadsgame.businessapi.controller;

import dadsgame.businessapi.entity.UserEntity;
import dadsgame.businessapi.exception.AlreadyExistsException;
import dadsgame.businessapi.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public List<UserEntity> getAllUsers() { return userService.getAllUser(); }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody UserEntity userEntity)  {
        if(userService.findByUserName(userEntity.getUsername()) != null) {
            throw new AlreadyExistsException("Account already exists.");
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userService.createUser(userEntity);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idUser}")
    public Optional<UserEntity> getUserById(@PathVariable int idUser) { return userService.getUserById(idUser); }

    @PutMapping("/{idUser}")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity userEntity){
        try {
            return new ResponseEntity<UserEntity>(userService.update(userEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
