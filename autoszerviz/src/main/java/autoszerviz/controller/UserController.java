package autoszerviz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import autoszerviz.service.UserService;
import autoszerviz.model.User;
import autoszerviz.utility.Response;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public Response<User> get(@PathVariable("id") int id) {
        Optional<User> optionalUser = userService.get(id);

        if (optionalUser.isPresent()) {
            return Response.ok(optionalUser.get());
        }

        return Response.error("No such user!");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<User> login(
        @RequestParam(value = "username") String username,
        @RequestParam(value = "password") String password
    ) {
        Optional<User> optionalUser = userService.login(username, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            return Response.ok(user);
        }

        return Response.error("Wrong username-password pair!");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<User> register(
        @RequestParam(value = "username") String username,
        @RequestParam(value = "password") String password
    ) {
        Optional<User> optionalUser = userService.register(username, password);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            return Response.ok(user);
        }

        return Response.error("Username is already in use!");
    }
}
