package autoszerviz.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import autoszerviz.repository.WorksheetRepository;
import autoszerviz.model.Worksheet;
import autoszerviz.repository.MechanicRepository;
import autoszerviz.repository.BookingRepository;
import autoszerviz.repository.MaterialRepository;
import autoszerviz.repository.PartnerRepository;

import java.util.Optional;
import java.util.ArrayList;

@Service
public class WorksheetService {
	@Autowired
    private PartnerRepository partnerRepository;
	@Autowired
    private MechanicRepository mechanicRepository;
	@Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private WorksheetRepository worksheetRepository;
	@Autowired
    private MaterialRepository materialRepository;

    public ArrayList<Worksheet> selectWorksheet(String date) {
		ArrayList<Worksheet> worksheets = new ArrayList<Worksheet>();
		ArrayList<Worksheet> allWorksheets = worksheetRepository.findAll();
		for(Worksheet w : allWorksheets) {
			if(w.getDate().equals(date)) {
				worksheets.add(w);
			}
		}
		
		return worksheets;
    }
	
	public Optional<Worksheet> addNew(int partnerid, String date, int mechanicid, int materialid) {
        Optional<Worksheet> optionalWorksheet = worksheetRepository.findByDateAndMechanicAndPartnerAndMaterial(date, mechanicRepository.findById(mechanicid).get(),partnerRepository.findById(partnerid).get(),materialRepository.findById(materialid).get());

        if (!optionalWorksheet.isPresent()) {
            Worksheet worksheet = new Worksheet();
			
            worksheet.setPartner(partnerRepository.findById(partnerid).get());
			worksheet.setDate(date);
			worksheet.setMechanic(mechanicRepository.findById(mechanicid).get());
			worksheet.setMaterial(materialRepository.findById(materialid).get());
            
            worksheetRepository.save(worksheet);

            return Optional.of(worksheet);
        }

        return Optional.empty();
    }
	
	public Optional<Worksheet> getWorksheetById(int id) {
		Optional<Worksheet> optionalWorksheet = worksheetRepository.findById(id);
		if (optionalWorksheet.isPresent()) {
			return optionalWorksheet;
		}
		return Optional.empty();
	}
	
	public void delete(int id) {
		worksheetRepository.delete(id);
	}
}
