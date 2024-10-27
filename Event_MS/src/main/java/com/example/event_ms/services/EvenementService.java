package com.example.event_ms.services;

import com.example.event_ms.entities.Evenement;
import com.example.event_ms.repositories.EvenementRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import java.util.List;

@Service
@AllArgsConstructor
public class EvenementService implements IEvenementService {

    EvenementRepository evenementRepository;
    FileStorageService fileStorageService;

    @Transactional
    @Override
    public Evenement addEvent(Evenement event){
            return evenementRepository.save(event);

    }

    @Override
    public Evenement getEvent(Long idEvent){
        return this.evenementRepository.findById(idEvent).orElse(null);
    }

    @Override
    public List<Evenement> getAllEvents(){
        return this.evenementRepository.findAll();
    }

    @Override
    public void deleteEvent(Long idEvent){
        this.evenementRepository.deleteById(idEvent);
    }

    @Override
    public Evenement updateEvent(Evenement event){
        Evenement oldEvent = evenementRepository.findById(event.getIdEvent()).orElse(null);
        event.setImageEvent(oldEvent.getImageEvent());
        return evenementRepository.save(event);
    }

    public Evenement handleImageFileUpload(MultipartFile fileImage, long id) {
        if (fileImage.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(fileImage);
        Evenement event = evenementRepository.findById(id).orElse(null);
        event.setImageEvent(fileName);
        return evenementRepository.save(event);
    }
    @Override
    public List<Evenement> getAllEventsSortedByDateAsc() {
        return evenementRepository.findAllByOrderByDateDebEventAsc();
    }

    @Override
    public List<Evenement> getAllEventsSortedByDateDesc() {
        return evenementRepository.findAllByOrderByDateDebEventDesc();
    }
}