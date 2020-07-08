package pos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping(value = "/{id}", produces = "Application/json")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserDTO user) throws Exception {
        User userUpdate = getService().update( id, getService().toUserModel(user));
        return new ResponseEntity<>(userUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "Application/text")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
