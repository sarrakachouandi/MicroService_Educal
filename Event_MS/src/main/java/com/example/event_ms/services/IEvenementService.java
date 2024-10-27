package com.example.event_ms.services;

import com.example.event_ms.entities.Evenement;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEvenementService {
    Evenement addEvent(Evenement event);

    Evenement getEvent(Long idEvent);

    List<Evenement> getAllEvents();

    void deleteEvent(Long idEvent);

    Evenement updateEvent(Evenement event);

    Evenement handleImageFileUpload(MultipartFile fileImage, long id);

    List<Evenement> getAllEventsSortedByDateAsc();

    List<Evenement> getAllEventsSortedByDateDesc();

}

