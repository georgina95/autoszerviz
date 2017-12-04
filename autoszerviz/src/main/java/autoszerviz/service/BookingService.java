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

    public Optional<Booking> book(int partnerid, String date, int mechanicid, String type, String comment) {
        Optional<Booking> optionalBooking = bookingRepository.findByDateAndMechanicid(date, mechanicid);

        if (!optionalBooking.isPresent()) {
            Booking booking = new Booking();

            booking.setPartnerid(partnerid);
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
