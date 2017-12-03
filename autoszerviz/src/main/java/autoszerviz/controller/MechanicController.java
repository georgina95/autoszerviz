package autoszerviz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import autoszerviz.service.MechanicService;
import autoszerviz.model.Mechanic;
import autoszerviz.utility.Response;

import java.util.Optional;

@RestController
@RequestMapping("/api/mechanic")
public class MechanicController {

    @Autowired
    private MechanicService mechanicService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response<Mechanic> login(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "password") String password
    ) {
        Optional<Mechanic> optionalMechanic = mechanicService.login(name, password);

        if (optionalMechanic.isPresent()) {
            Mechanic mechanic = optionalMechanic.get();
            
            return Response.ok(mechanic);
        }

        return Response.error("Wrong name-password pair!");
    }
}
