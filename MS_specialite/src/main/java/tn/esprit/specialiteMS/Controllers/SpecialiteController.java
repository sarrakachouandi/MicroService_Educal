package tn.esprit.specialiteMS.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.specialiteMS.Services.FileStorageService;
import tn.esprit.specialiteMS.Services.SpecialiteService;
import tn.esprit.specialiteMS.entities.Specialite;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@AllArgsConstructor
@RequestMapping("/specialites")
public class SpecialiteController {
    SpecialiteService specialiteService;
    FileStorageService fileStorageService;

    @GetMapping
    List<Specialite> getAll(){
        return specialiteService.getAll();
    }

    @PostMapping
    Specialite add(@RequestBody Specialite specialite){
        return  specialiteService.add(specialite);
    }

    @GetMapping("/{id}")
    Specialite getById(@PathVariable long id){
        return specialiteService.getById(id);
    }

    @PutMapping
    Specialite update(@RequestBody Specialite specialite){
        return  specialiteService.update(specialite);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable long id){
        specialiteService.delete(id);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @PostMapping("/uploadPdf/{id}")
    public Specialite handlePlanEtudeFileUpload(@RequestParam("file") MultipartFile file,@PathVariable long id) {
        return specialiteService.handlePlanEtudeFileUpload(file,id);
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @PostMapping("/uploadImage/{id}")
    public Specialite handleImageFileUpload(@RequestParam("fileImage") MultipartFile fileImage,@PathVariable long id) {
        return specialiteService.handleImageFileUpload(fileImage,id);
    }
}
