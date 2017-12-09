package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Partner;

@Repository
public interface PartnerRepository
    extends CrudRepository<Partner, Integer> {

    Optional<Partner> findById(int id);
    Optional<Partner> findByName(String name);
}
