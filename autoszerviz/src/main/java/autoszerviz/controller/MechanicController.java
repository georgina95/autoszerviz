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

import autoszerviz.service.WorksheetService;
import autoszerviz.model.Worksheet;
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
    private WorksheetService worksheetService;
	@Autowired
    private BookingService bookingService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
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
	
	//#####BOOKING######
	
	@RequestMapping(value = "/booklist", method = RequestMethod.POST)
	public Response< ArrayList<Booking> > bookList(){
		if(session.getMechanic() != null) {
			ArrayList<Booking> fullList = bookingService.getList();
			ArrayList<Booking> bookingList = new ArrayList<Booking>();
			if (fullList != null) {
				for(Booking b : fullList) {
					if(b.getMechanic().getId() == session.getMechanic().getId())
						bookingList.add(b);
				}
				
				return Response.ok(bookingList);
			}
			return Response.error("Booking list cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
	
	//#####WORKSHEET######
	
	@RequestMapping(value = "/worksheet", method = RequestMethod.POST)
	public Response< ArrayList<Worksheet> > worksheetList(
		@RequestParam(value = "date") String date
	){
		if(session.getMechanic() != null) {
			ArrayList<Worksheet> fullList = worksheetService.selectWorksheet(date);
			ArrayList<Worksheet> wsList = new ArrayList<Worksheet>();
			if (fullList != null) {
				for(Worksheet w : fullList) {
					if(w.getMechanic().getId() == session.getMechanic().getId())
						wsList.add(w);
				}//http://localhost:8080/api/mechanic/addnew?partnerid=2&date=2017-07-02%2016:00:00&mechanicid=1&materialid=2&partid=1
				
				return Response.ok(wsList);
			}
			return Response.error("Worksheet list cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
	
	@RequestMapping(value = "/addnew", method = RequestMethod.POST)
    public Response<Worksheet> addNew(
        @RequestParam(value = "partnerid") int partnerid,
        @RequestParam(value = "date") String date,
        @RequestParam(value = "materialid") int materialid,
        @RequestParam(value = "partid") int partid
		
    ) {
        Optional<Worksheet> optionalWorksheet = worksheetService.addNew(partnerid, date, session.getMechanic().getId(), materialid, partid);

        if (optionalWorksheet.isPresent()) {
            Worksheet worksheet = optionalWorksheet.get();
            
            return Response.ok(worksheet);
        }

        return Response.error("Row already exist!");
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Response<Worksheet> delete(
	@RequestParam(value = "id") int id
		){
		Mechanic mechanic = session.getMechanic();
		if(mechanic != null) {
			Optional<Worksheet> optionalWorksheet = worksheetService.getWorksheetById(id);
			if (optionalWorksheet.isPresent()) {
				Worksheet worksheet = optionalWorksheet.get();
				worksheetService.delete(id);
				return Response.ok(worksheet);
			}
			return Response.error("Worksheet cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
}
