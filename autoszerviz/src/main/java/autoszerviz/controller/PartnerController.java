package autoszerviz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import autoszerviz.service.PartnerSession;
import autoszerviz.service.PartnerService;
import autoszerviz.model.Partner;
import autoszerviz.utility.Response;

import autoszerviz.service.BookingService;
import autoszerviz.model.Booking;

import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {
	@Autowired
	PartnerSession session;
    @Autowired
    private PartnerService partnerService;
	@Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response<Partner> login(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "password") String password
    ) {
        Optional<Partner> optionalPartner = partnerService.login(name, password);

        if (optionalPartner.isPresent()) {
			if(session.getPartner() == null) {
				Partner partner = optionalPartner.get();
				
				session.setPartner(partner);
				return Response.ok(partner);
			}
			return Response.error("Someone already logged in!");
        }
        return Response.error("Wrong name-password pair!");
    }
	
	@RequestMapping("/logout")
    public Response logout() {
        session.setPartner(null);
        return Response.ok(false);
	}

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response<Partner> register(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "address") String address,
        @RequestParam(value = "phonenumber") String phonenumber,
        @RequestParam(value = "password") String password
    ) {
        Optional<Partner> optionalPartner = partnerService.register(name, address, phonenumber, password);

        if (optionalPartner.isPresent()) {
            Partner partner = optionalPartner.get();
            
            return Response.ok(partner);
        }

        return Response.error("Name is already in use!");
    }
	
	//#####BOOKING######
	
	@RequestMapping(value = "/booklist", method = RequestMethod.POST)
	public Response< ArrayList<Booking> > bookList(
        @RequestParam(value = "mechanicid") int mechanicid
	){
		if(session.getPartner() != null) {
			ArrayList<Booking> fullList = bookingService.getList();
			ArrayList<Booking> bookingList = new ArrayList<Booking>();
			if (fullList != null) {
				for(Booking b : fullList) {
					if(b.getMechanic().getId() == mechanicid) {
						if(b.getPartner().getId() != session.getPartner().getId())
							b.partner = null;
						bookingList.add(b);
					}
				}
				
				if(bookingList.size() != 0)
					return Response.ok(bookingList);
				else
					return Response.error("Booklist list with this mechanic cannot be found!");
			}
			return Response.error("Booking list cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
	
	@RequestMapping(value = "/booklist/book", method = RequestMethod.POST)
    public Response<Booking> book(
        @RequestParam(value = "date") String date,
        @RequestParam(value = "mechanicid") int mechanicid,
        @RequestParam(value = "type") String type,
        @RequestParam(value = "comment") String comment
		
    ) {
		if(session.getPartner() != null) {
			Optional<Booking> optionalBooking = bookingService.book(session.getPartner().getId(), date, mechanicid, type, comment);
		
			if (optionalBooking.isPresent()) {
				Booking booking = optionalBooking.get();
				
				return Response.ok(booking);
			}

			return Response.error("In this time the mechanic is already busy!");
		}
		return Response.error("You are not logged in!");
    }
	
	
	@RequestMapping(value = "/booklist/delete", method = RequestMethod.POST)
	public Response<Booking> delete(
	@RequestParam(value = "id") int id
		){
		Partner partner = session.getPartner();
		if(partner != null) {
			Optional<Booking> optionalBooking = bookingService.getBookingById(id);
			if (optionalBooking.isPresent()) {
				Booking booking = optionalBooking.get();
				if(booking.getPartner().getId() == partner.getId()) {
					bookingService.delete(id);
					return Response.ok(booking);
				}
				return Response.error("You don't have the permission to delete other partner's bookings!");
			}
			return Response.error("Booking cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
}
