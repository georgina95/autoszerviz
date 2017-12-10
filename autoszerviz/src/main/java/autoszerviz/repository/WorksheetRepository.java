package autoszerviz.repository;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Worksheet;
import autoszerviz.model.Mechanic;
import autoszerviz.model.Partner;
import autoszerviz.model.Material;
import autoszerviz.model.Part;

@Repository
public interface WorksheetRepository
    extends CrudRepository<Worksheet, Integer> {
    Optional<Worksheet> findById(int id);
	ArrayList<Worksheet> findAll();
	Optional<Worksheet> findByDateAndMechanic(String date, Mechanic mechanic);
	Optional<Worksheet> findByDateAndMechanicAndPartnerAndMaterialAndPart(String date, Mechanic mechanic, Partner partner, Material material, Part part);
}
