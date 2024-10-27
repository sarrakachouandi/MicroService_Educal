package com.example.event_ms.controllers;


import com.example.event_ms.entities.Evenement;
import com.example.event_ms.repositories.EvenementRepository;
import com.example.event_ms.services.EvenementService;
import com.example.event_ms.services.FileStorageService;
import com.example.event_ms.services.IEvenementService;
import lombok.AllArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/event")
@AllArgsConstructor


public class EvenementController {
    IEvenementService eventService;
    FileStorageService fileStorageService ;
    EvenementRepository evenementRepository ;

    EvenementService evenementService ;

    @PostMapping("/addEvent")
    Evenement addEvent(@RequestBody Evenement event){
        return eventService.addEvent(event);
    }

    @GetMapping("/getOneEvent/{id}")
    Evenement getEvent(@PathVariable Long id){
        return eventService.getEvent(id);
    }

    @GetMapping("/events")
    List<Evenement> getAllEvent(){
        return eventService.getAllEvents();
    }

    @DeleteMapping("/deleteEvent/{id}")
    void deleteEvent(@PathVariable Long id){
        this.eventService.deleteEvent(id);
    }

    @PutMapping("/updateEvent")
    Evenement updateEvent(@RequestBody Evenement event){
        return  this.eventService.updateEvent(event);
    }

  @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @PostMapping("/events/uploadImage/{id}")
    public Evenement handleImageFileUpload(@RequestParam("fileImage") MultipartFile fileImage, @PathVariable long id) {
        return eventService.handleImageFileUpload(fileImage,id);
    }
    @GetMapping("/sortedByDateAsc")
    public List<Evenement> getAllEventsSortedByDateAsc() {
        return evenementService.getAllEventsSortedByDateAsc();
    }

    @GetMapping("/sortedByDateDesc")
    public List<Evenement> getAllEventsSortedByDateDesc() {
        return evenementService.getAllEventsSortedByDateDesc();
    }

}