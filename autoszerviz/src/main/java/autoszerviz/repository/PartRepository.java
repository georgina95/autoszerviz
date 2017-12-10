package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Part;

@Repository
public interface PartRepository
    extends CrudRepository<Part, Integer> {

	Optional<Part> findById(int id);
    Optional<Part> findByName(String name);
}
