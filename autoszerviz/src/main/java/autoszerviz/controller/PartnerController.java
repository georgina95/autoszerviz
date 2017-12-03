package autoszerviz.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import autoszerviz.service.PartnerService;
import autoszerviz.model.Partner;
import autoszerviz.utility.Response;

import java.util.Optional;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Response<Partner> login(
        @RequestParam(value = "name") String name,
        @RequestParam(value = "password") String password
    ) {
        Optional<Partner> optionalPartner = partnerService.login(name, password);

        if (optionalPartner.isPresent()) {
            Partner partner = optionalPartner.get();
            
            return Response.ok(partner);
        }

        return Response.error("Wrong name-password pair!");
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
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
}
