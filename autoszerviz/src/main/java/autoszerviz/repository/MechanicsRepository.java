package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Mechanics;

@Repository
public interface MechanicsRepository
    extends CrudRepository<Mechanics, Integer> {

    Optional<Mechanics> findByName(String name);
}
