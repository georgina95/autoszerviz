package autoszerviz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import autoszerviz.service.BookingService;
import autoszerviz.model.Booking;
import autoszerviz.utility.Response;

import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	
    @Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public Response<Booking> book(
        @RequestParam(value = "partnerid") int partnerid,
        @RequestParam(value = "date") String date,
        @RequestParam(value = "mechanicid") int mechanicid,
        @RequestParam(value = "type") String type,
        @RequestParam(value = "comment") String comment
		
    ) {
        Optional<Booking> optionalBooking = bookingService.book(partnerid, date, mechanicid, type, comment);

        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            
            return Response.ok(booking);
        }

        return Response.error("Name is already in use!");
    }
	
}