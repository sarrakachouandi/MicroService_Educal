package tn.esprit.universite.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.universite.entities.Universite;

import java.util.List;

public interface IUniversiteService {
    Universite addUniversite(Universite universite , MultipartFile imageFile);
    Universite getUniversite(Long id);
    List<Universite> getAllUniversites();
    void deleteUniversite(long idUniversite);
    Universite updateUniversite(Universite universite,MultipartFile imageFile);
    public List<Universite> rechercherParNom(String nomUniversite);
}
