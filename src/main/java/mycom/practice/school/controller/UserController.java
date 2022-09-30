package mycom.practice.school.controller;

import mycom.practice.school.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController("users")
public class UserController {

    @PostMapping("/authenticate")
    private User authenticateUser(@RequestParam String username, @RequestParam String password){
        System.out.println("authenticating users");
        return new User(username, "encryptedP@ssw0rd", "earth", "101");
    }
}
