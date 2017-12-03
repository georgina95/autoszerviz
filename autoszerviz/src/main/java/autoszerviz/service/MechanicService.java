package autoszerviz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import autoszerviz.repository.MechanicRepository;
import autoszerviz.model.Mechanic;

import java.util.Optional;

@Service
public class MechanicService {
    @Autowired
    private MechanicRepository mechanicRepository;

    public Optional<Mechanic> login(String name, String password) {
        Optional<Mechanic> optionalMechanic = mechanicRepository.findByName(name);

        return optionalMechanic.filter(mechanic -> mechanic.getPassword().equals(password));
    }

        return Optional.empty();
    }
}
