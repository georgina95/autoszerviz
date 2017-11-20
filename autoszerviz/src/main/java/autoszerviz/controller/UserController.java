package autoszerviz.controller;
 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import autoszerviz.repository.UserRepository;
import autoszerviz.model.User;
 
@RestController
@RequestMapping("/api/user")
public class UserController {
 
    @Autowired
    private UserRepository userRepository;
 
    @RequestMapping("/get")
    public User get(
        @RequestParam(value = "username") String username
    ) {
        return userRepository.findByUsername(username).get();
    }
}
 
