package autoszerviz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import autoszerviz.repository.BookingRepository;
import autoszerviz.model.Booking;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Optional<Booking> register(String name, String date, int mechanicid, String type, String comment) {
        Optional<Booking> optionalBooking = bookingRepository.findByMechanicid(mechanicid);

        if (!optionalBooking.isPresent() && (!optionalBooking.date.equals(date) || optionalBooking.mechanicid != mechanicid)) {
            Booking booking = new Booking();

            booking.setName(name);
			booking.setDate(date);
			booking.setMechanicid(mechanicid);
            booking.setType(type);
            booking.setComment(comment);
			
            bookingRepository.save(booking);

            return Optional.of(booking);
        }

        return Optional.empty();
    }
}
