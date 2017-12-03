package autoszerviz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import autoszerviz.repository.PartnerRepository;
import autoszerviz.model.Partner;

import java.util.Optional;

@Service
public class PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public Optional<Partner> login(String name, String password) {
        Optional<Partner> optionalPartner = partnerRepository.findByName(name);

        return optionalPartner.filter(partner -> partner.getPassword().equals(password));
    }

    public Optional<Partner> register(String name, String address, String phonenumber, String password) {
        Optional<Partner> optionalPartner = partnerRepository.findByName(name);

        if (!optionalPartner.isPresent()) {
            Partner partner = new Partner();

            partner.setName(name);
			partner.setAddress(address);
			partner.setPhonenumber(phonenumber);
            partner.setPassword(password);

            partnerRepository.save(partner);

            return Optional.of(partner);
        }

        return Optional.empty();
    }

    public Optional<Partner> get(int id) {
        return partnerRepository.findById(id);
    }
}
