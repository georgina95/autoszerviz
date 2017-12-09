package autoszerviz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import autoszerviz.service.MechanicSession;
import autoszerviz.service.MechanicService;
import autoszerviz.model.Mechanic;
import autoszerviz.utility.Response;

import autoszerviz.service.BookingService;
import autoszerviz.model.Booking;

import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/mechanic")
public class MechanicController {
	@Autowired
	MechanicSession session;
    @Autowired
    private MechanicService mechanicService;
	@Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response<Mechanic> login(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "password") String password
    ) {
        Optional<Mechanic> optionalMechanic = mechanicService.login(name, password);

        if (optionalMechanic.isPresent()) {
            Mechanic mechanic = optionalMechanic.get();
            
			session.setMechanic(mechanic);
            return Response.ok(mechanic);
        }

        return Response.error("Wrong name-password pair!");
    }
	
	@RequestMapping("/logout")
    public Response logout() {
        session.setMechanic(null);
        return Response.ok(false);
	}
	
	@GetMapping("/test")
    public Response getMechanic() {
        if (session.getMechanic() == null) {
            return Response.ok(false);
        } else {
            return Response.ok(session.getMechanic());
        }
	}
	
	
	//#####BOOK######
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public Response< ArrayList<Booking> > booklist(){
		if(session.getMechanic() != null) {
			ArrayList<Booking> bookingList = bookingService.getList();
			if (bookingList != null) {
				ArrayList<Booking> filteredList = new ArrayList<Booking>();
				for(Booking b : bookingList) {
					if(b.mechanic.getId() == session.getMechanic().getId())
						filteredList.add(b);
				}
				
				return Response.ok(filteredList);
			}
			return Response.error("Booking list cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
}
