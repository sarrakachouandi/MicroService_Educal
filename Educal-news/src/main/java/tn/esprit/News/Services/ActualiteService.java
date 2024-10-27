package tn.esprit.News.Services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.News.Entities.Actualite;
import tn.esprit.News.Repositories.ActualiteRepository;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ActualiteService implements IActualiteService{
    ActualiteRepository actualiteRepository;

    FileStorageService fileStorageService;


    @Transactional
    @Override
    public Actualite addActualite(Actualite actualite){
        try {
            Date currentDate = new Date();
            actualite.setDateActualite(currentDate);
            return actualiteRepository.save(actualite);
        } catch (Exception e) {
            e.printStackTrace(); // Log or print the exception
            throw e; // Re-throw the exception if needed
        }
    }

    @Override
    public Actualite getActualite(Long idActualite){
        return this.actualiteRepository.findById(idActualite).orElse(null);
    }

    @Override
    public List<Actualite> getAllActualites(){
        return  this.actualiteRepository.findAll();
    }

    @Override
    public void deleteActualiteById(Long idActualite){
        this.actualiteRepository.deleteById(idActualite);
    }

    @Override
    public Actualite updateActualite(Actualite actualite) {
        Actualite oldActualite = actualiteRepository.findById(actualite.getIdActualite()).orElse(null);
        actualite.setImageActualite(oldActualite.getImageActualite());
        return actualiteRepository.save(actualite);
    }

    public Actualite handleImageFileUpload(MultipartFile fileImage, long id) {
        if (fileImage.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(fileImage);
        Actualite actualite = actualiteRepository.findById(id).orElse(null);
        actualite.setImageActualite(fileName);
        return actualiteRepository.save(actualite);
    }

    @Override
    public String shareFb(Long id){
        String appId = "232528662540085";
        String appSecret = "60988e9928012f06c205e07717bb4196";
        String accessTokenString = "EAADTe8xUrzUBOwgL3BQmvYAVxXUjBZB0r9jwYdKZC0xJmcRN2Jg71FM8Gq0UB9RBnpNawZA1UZAc5sSjPlAMEWDg5YHot27sHtB1uzFympoyXc4cjZCXx5TtnZAU7W8f30GlUb4hTCm7M7AyJgUUP60enhgfyj9RvSPqJOrug8ILL3RlmYTdvicBx4ZAMoSxQ7W57jlMRpNXzaA5b50l46KYHgZD";

        // Set up Facebook4J
        Facebook facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthAccessToken(new AccessToken(accessTokenString, null));

        // Post a status message
        Actualite actualite = actualiteRepository.findById(id).orElse(null);

        String message = "New Post :" + "\n"+ actualite.getTitreActualite() + "\n" + actualite.getDescriptionActualite()+ "\n" + actualite.getDateActualite()+ "\n";
        try {
            facebook.postStatusMessage(message);
            return "Status message posted successfully.";
        } catch (FacebookException e) {
            e.printStackTrace();
            System.err.println("Error posting status message: " + e.getMessage());
            return  "Erreur";
        }
    }


}
