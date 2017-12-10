package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Mechanic;

@Repository
public interface MechanicRepository
    extends CrudRepository<Mechanic, Integer> {

	Optional<Mechanic> findById(int id);
    Optional<Mechanic> findByName(String name);
}
