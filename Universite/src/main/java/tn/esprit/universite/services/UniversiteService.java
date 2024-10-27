package tn.esprit.universite.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.universite.repositories.UniversiteRepository;
import tn.esprit.universite.entities.Universite;

import java.util.List;
@Service
@AllArgsConstructor
public class UniversiteService implements IUniversiteService {

    FileStorageService fileStorageService;
    UniversiteRepository universiteRepository;

    @Override
    public Universite addUniversite(Universite universite, MultipartFile imageFile) {
        String imageUrl = fileStorageService.storeFile(imageFile);
        universite.setImage(imageUrl);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite getUniversite(Long id) {
        return universiteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public void deleteUniversite(long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }

    @Override
    public Universite updateUniversite(Universite universite,MultipartFile imageFile) {
        Universite univ = universiteRepository.findById(universite.getIdUniversite()).orElse(null);
        if (univ != null) {
            String image = fileStorageService.storeFile(imageFile);
            universite.setImage(image);
            universiteRepository.save(universite);
        }
            return univ;
    }

    public List<Universite> rechercherParNom(String nomUniversite) {
        return universiteRepository.findByNomUniversiteContainingIgnoreCase(nomUniversite);
    }

}
