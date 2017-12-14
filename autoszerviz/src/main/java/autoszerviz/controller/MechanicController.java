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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response<Mechanic> login(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "password") String password
    ) {
        Optional<Mechanic> optionalMechanic = mechanicService.login(name, password);

        if (optionalMechanic.isPresent()) {
			if(session.getMechanic() == null) {
				Mechanic mechanic = optionalMechanic.get();
            
				session.setMechanic(mechanic);
				return Response.ok(mechanic);
			}
			return Response.error("Someone already logged in!");
        }
        return Response.error("Wrong name-password pair!");
    }
	
	@RequestMapping("/logout")
    public Response logout() {
        session.setMechanic(null);
        return Response.ok(false);
	}
	
	//#####BOOKING######
	
	@RequestMapping(value = "/booklist", method = RequestMethod.GET)
	public Response< ArrayList<Booking> > bookList(){
		if(session.getMechanic() != null) {
			ArrayList<Booking> fullList = bookingService.getList();
			ArrayList<Booking> bookingList = new ArrayList<Booking>();
			if (fullList != null) {
				for(Booking b : fullList) {
					if(b.getMechanic().getId() == session.getMechanic().getId())
						bookingList.add(b);
				}
				
				if(bookingList.size() != 0)
					return Response.ok(bookingList);
				else
					return Response.error("Booklist list with this date cannot be found!");
			}
			return Response.error("Booking list cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
	
	//#####WORKSHEET######
	
	@RequestMapping(value = "/worksheet", method = RequestMethod.GET)
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
				}
				
				if(wsList.size() != 0)
					return Response.ok(wsList);
				else
					return Response.error("Worksheet list with this date cannot be found!");
			}
			return Response.error("Worksheet list cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
	
	@RequestMapping(value = "worksheet/addnew", method = RequestMethod.GET)
    public Response<Worksheet> addNew(
        @RequestParam(value = "partnerid") int partnerid,
        @RequestParam(value = "date") String date,
        @RequestParam(value = "materialid") int materialid
		
    ) {
		if(session.getMechanic() != null) {
			Optional<Worksheet> optionalWorksheet = worksheetService.addNew(partnerid, date, session.getMechanic().getId(), materialid);

			if (optionalWorksheet.isPresent()) {
				Worksheet worksheet = optionalWorksheet.get();
				
				return Response.ok(worksheet);
			}

			return Response.error("Row already exist!");
		}
		return Response.error("You are not logged in!");
    }
	
	@RequestMapping(value = "worksheet/delete", method = RequestMethod.GET)
	public Response<Worksheet> delete(
	@RequestParam(value = "id") int id
		){
		Mechanic mechanic = session.getMechanic();
		if(mechanic != null) {
			Optional<Worksheet> optionalWorksheet = worksheetService.getWorksheetById(id);
			if (optionalWorksheet.isPresent()) {
				Worksheet worksheet = optionalWorksheet.get();
				if(worksheet.getMechanic().getId() == mechanic.getId()) {
					worksheetService.delete(id);
					return Response.ok(worksheet);
				}
				return Response.error("You don't have the permission to delete other mechanic's worksheets!");
			}
			return Response.error("Worksheet cannot be found!");
		}
		return Response.error("You are not logged in!");
	}
}
