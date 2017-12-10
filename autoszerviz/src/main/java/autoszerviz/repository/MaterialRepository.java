package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Material;

@Repository
public interface MaterialRepository
    extends CrudRepository<Material, Integer> {

	Optional<Material> findById(int id);
    Optional<Material> findByName(String name);
}
