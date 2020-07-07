package pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 5997d1c8e6a31ad82d2c401406c2fb3a5ba30f3b

import pos.dto.UserDTO;
import pos.model.User;
import pos.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    public UserService getService() {
        return this.userService;
    }

    @GetMapping(value = "", produces = "Application/json")
    public ResponseEntity<List<UserDTO>> list() {
        List<UserDTO> users = getService().get();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "Application/json")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Integer id) {
        User user = getService().findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = "Application/json")
    public ResponseEntity<User> create(@RequestBody UserDTO userDTO) throws Exception {
        User userSalved = getService().create(getService().toUserModel(userDTO));
        return new ResponseEntity<>(userSalved, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/", produces = "Application/json")
    public ResponseEntity<User> update(@RequestBody UserDTO userDTO) throws Exception {

        User userUpdate = getService().update(getService().toUserModel(userDTO));

        return new ResponseEntity<User>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "Application/text")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
