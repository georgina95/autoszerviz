package autoszerviz.controller;
 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import autoszerviz.repository.MechanicsRepository;
import autoszerviz.model.Mechanics;
 
@RestController
@RequestMapping("/api/mechanics")
public class MechanicsController {
 
    @Autowired
    private MechanicsRepository mechanicsRepository;
 
    @RequestMapping("/get")
    public Mechanics get(
        @RequestParam(value = "name") String name
    ) {
        return mechanicsRepository.findByName(name).get();
    }
}
 
