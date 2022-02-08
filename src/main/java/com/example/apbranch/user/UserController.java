package com.example.apbranch.user;

import com.example.apbranch.response.Pair;
import com.example.apbranch.response.Response;
import com.example.apbranch.response.ResponseCode;
import com.example.apbranch.user.roles.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable UUID id, @PathVariable UUID userId) {
        if (userService.getUser(userId).u.getRole().equals(Roles.USER)) {
            return ResponseEntity.status(ResponseCode.FORBIDDEN).body("Forbidden");
        }
        Pair<Response, User> mainResponse = userService.getUser(userId);
        Response response = mainResponse.r;
        if (response.getStatus().equals(ResponseCode.OK))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(ResponseCode.NOT_FOUND).body(response.getMessage());
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDto) {
        Response response = userService.addUser(userDto);
        if (response.getStatus().equals(ResponseCode.CREATED))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable UUID id, @RequestBody UserDTO userDto) {
        Response response = userService.editUser(id, userDto);
        if (response.getStatus().equals(ResponseCode.CREATED))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response.getMessage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID id) {
        Response response = userService.deleteUser(id);
        if (response.getStatus().equals(ResponseCode.OK))
            return ResponseEntity.ok(response.getMessage());
        return ResponseEntity.status(ResponseCode.NOT_FOUND).body(response.getMessage());
    }
}
