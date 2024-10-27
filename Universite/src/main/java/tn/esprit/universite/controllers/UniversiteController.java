package tn.esprit.universite.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.universite.services.FileStorageService;
import tn.esprit.universite.services.IUniversiteService;
import tn.esprit.universite.entities.Universite;

import java.util.List;
@CrossOrigin("http://localhost:4200")

@RestController
@AllArgsConstructor
@RequestMapping("/universite")

public class UniversiteController {

    IUniversiteService universiteService;
    FileStorageService  fileStorageService ;

    @PostMapping("/adduniversite")
    Universite addUniversite (@ModelAttribute Universite universite, @RequestParam(value = "file",required = false) MultipartFile imageFile){
        String imageUrl = fileStorageService.storeFile(imageFile);
        universite.setImage(imageUrl);
        return universiteService.addUniversite(universite,imageFile);
    }

    @GetMapping("/universite/{id}")
    Universite retrieveUniversite(@PathVariable Long id){

        return universiteService.getUniversite(id);
    }

    @GetMapping("/universites")
    List<Universite> retrieveUniversite(){
        return universiteService.getAllUniversites();}

    @PatchMapping("/universite")
    public Universite updateUniversite(@ModelAttribute Universite universite, @RequestParam(value = "file", required = false) MultipartFile imageFile) {
        if (imageFile != null){
            String image = fileStorageService.storeFile(imageFile);
            universite.setImage(image); }
        if (imageFile == null){
            universite.setImage(universite.getImage()); }
        return universiteService.updateUniversite(universite,imageFile);
    }

    @DeleteMapping("/universite/{id}")
    void deleteUniversite(@PathVariable long id){
        universiteService.deleteUniversite(id);
    }

    @GetMapping("/searchUnivarsite/{nomUniversite}")
    public ResponseEntity<List<Universite>> rechercherParNom(@PathVariable String nomUniversite) {
        List<Universite> result = universiteService.rechercherParNom(nomUniversite);
        return ResponseEntity.ok(result);
    }

}

