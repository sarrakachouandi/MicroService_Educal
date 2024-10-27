package tn.esprit.News.Services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.News.Entities.Actualite;

import java.util.List;

public interface IActualiteService {
    Actualite addActualite(Actualite actualite);

    Actualite getActualite(Long idActualite);

    List<Actualite> getAllActualites();

    void deleteActualiteById(Long idActualite);

    Actualite updateActualite(Actualite actualite);

    Actualite handleImageFileUpload(MultipartFile fileImage, long id);

    String shareFb(Long id);

    //**********************************************

}
