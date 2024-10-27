package com.esprit.microservices.foyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoyerService {
    @Autowired
    private FoyerRepository foyerRepository;
    public Foyer addFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }
    public List<Foyer> getFoyer(){
        return foyerRepository.findAll();
    }


    public List<Foyer> findByCapacityFoyerLessThan(long capacity) {
        return foyerRepository.findByCapacityFoyerLessThan(capacity);
    }

    public List<Foyer> getFoyersByCapacities(boolean lowCapacity, boolean mediumCapacity, boolean highCapacity) {
        // Implement your logic to filter foyers based on capacities
        if (lowCapacity) {
            // Add logic for low capacity filtering
        }
        if (mediumCapacity) {
            // Add logic for medium capacity filtering
        }
        if (highCapacity) {
            // Add logic for high capacity filtering
        }

        return foyerRepository.findAll(); // Return all foyers for now
    }

    public Foyer updateFoyer(long id, Foyer newFoyer) {
        if (foyerRepository.findById(id).isPresent()) {
            Foyer existingFoyer = foyerRepository.findById(id).get();
            existingFoyer.setNomFoyer(newFoyer.getNomFoyer());
            existingFoyer.setCapacityFoyer(newFoyer.getCapacityFoyer());

            return foyerRepository.save(existingFoyer);
        } else
            return null;
    }
    public String deleteFoyer(long id) {
        if (foyerRepository.findById(id).isPresent()) {
            foyerRepository.deleteById(id);
            return "Foyer supprimé";
        } else
            return "Foyer non supprimé";
    }


    public Foyer getFoyer(Long id) {
        return foyerRepository.findById(id).orElse(null);
    }
}