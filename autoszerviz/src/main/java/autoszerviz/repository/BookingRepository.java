package autoszerviz.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import autoszerviz.model.Booking;

@Repository
public interface BookingRepository
    extends CrudRepository<Booking, Integer> {

    Optional<Booking> findById(int id);
	Optional<Booking> findByDateAndMechanicid(String date, int mechanicid);
}
