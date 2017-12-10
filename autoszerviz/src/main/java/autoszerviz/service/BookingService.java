package autoszerviz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import autoszerviz.repository.BookingRepository;
import autoszerviz.model.Booking;
import autoszerviz.repository.MechanicRepository;
import autoszerviz.repository.PartnerRepository;

import java.util.Optional;
import java.util.ArrayList;

@Service
public class BookingService {
	@Autowired
    private PartnerRepository partnerRepository;
	@Autowired
    private MechanicRepository mechanicRepository;
    @Autowired
    private BookingRepository bookingRepository;

    public Optional<Booking> book(int partnerid, String date, int mechanicid, String type, String comment) {
        Optional<Booking> optionalBooking = bookingRepository.findByDateAndMechanic(date, mechanicRepository.findById(mechanicid).get());

        if (!optionalBooking.isPresent()) {
            Booking booking = new Booking();
			
            booking.setPartner(partnerRepository.findById(partnerid).get());
			booking.setDate(date);
			booking.setMechanic(mechanicRepository.findById(mechanicid).get());
			switch(type) {
			case "COMPULSORY_SERVICE": booking.setType(Booking.Type.COMPULSORY_SERVICE); break;
			case "TECHNICAL_EXAMINATION": booking.setType(Booking.Type.TECHNICAL_EXAMINATION); break;
			case "MALFUNCTION": booking.setType(Booking.Type.MALFUNCTION); break;
			}
            
            booking.setComment(comment);
			
            bookingRepository.save(booking);

            return Optional.of(booking);
        }

        return Optional.empty();
    }
	
	public ArrayList<Booking> getList() {
		ArrayList<Booking> bookingList = bookingRepository.findAll();
		
		return bookingList;
	}
	
	public Optional<Booking> getBookingById(int id) {
		Optional<Booking> optionalBooking = bookingRepository.findById(id);
		if (optionalBooking.isPresent()) {
			return optionalBooking;
		}
		return Optional.empty();
	}
	
	public void delete(int id) {
		bookingRepository.delete(id);
	}
}
