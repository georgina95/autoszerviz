package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.User;

@Repository
public interface UserRepository
    extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String name);
}
