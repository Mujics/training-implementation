package wolox.bootstrap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wolox.bootstrap.models.User;
import wolox.bootstrap.repositories.UserRepository;

@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    // Create
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Find All
    @GetMapping("/view")
    public Iterable findAll() {
        return userRepository.findAll();
    }

    // Find One
    @GetMapping("/view/{id}")
    public User findById(@PathVariable int id) throws RuntimeException {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    // Delete
    @DeleteMapping("/view/{id}")
    public void delete(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
