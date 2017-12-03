package autoszerviz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import autoszerviz.repository.UserRepository;
import autoszerviz.model.User;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        return optionalUser.filter(user -> user.getPassword().equals(password));
    }

    public Optional<User> register(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (!optionalUser.isPresent()) {
            User user = new User();

            user.setUsername(username);
            user.setPassword(password);

            userRepository.save(user);

            return Optional.of(user);
        }

        return Optional.empty();
    }

    public Optional<User> get(int id) {
        return userRepository.findById(id);
    }
}
