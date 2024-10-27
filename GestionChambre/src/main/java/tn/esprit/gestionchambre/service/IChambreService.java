package tn.esprit.gestionchambre.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gestionchambre.entities.Chambre;
import tn.esprit.gestionchambre.entities.TypeChambre;

import java.util.List;
import java.util.Map;

public interface IChambreService {

   Chambre addChambre(Chambre chambre);

    Chambre getChambre(Long id);

    List<Chambre> getAllChambres();

    void deleteChambre(long idChambre);

    Chambre updateChambre(Chambre chambre);
    public Chambre handleImageFileUpload(MultipartFile fileImage, long id) ;

    Map<TypeChambre, Long> calculerNombreChambresParType();

}


