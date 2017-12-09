package autoszerviz.repository;

import java.util.Optional;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Booking;
import autoszerviz.model.Mechanic;

@Repository
public interface BookingRepository
    extends CrudRepository<Booking, Integer> {

    Optional<Booking> findById(int id);
	ArrayList<Booking> findAll();
	Optional<Booking> findByDateAndMechanic(String date, Mechanic mechanic);
}
