package autoszerviz.repository;

// goo.gl/tV6U5T
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.User;

@Repository
public interface UserRepository
    extends CrudRepository<User, Integer> {

    Optional<User> findById(int id);
    Optional<User> findByUsername(String name);
}
