package tn.esprit.gestionchambre.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.gestionchambre.entities.Chambre;
import tn.esprit.gestionchambre.entities.TypeChambre;
import tn.esprit.gestionchambre.service.ChambreService;

import tn.esprit.gestionchambre.service.FileStorageService;
import tn.esprit.gestionchambre.service.IChambreService;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Chambre")
public class ChambreController {
    IChambreService chambreService;
    FileStorageService fileStorageService;

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @PostMapping("/uploadImage/{id}")
    public Chambre handleImageFileUpload(@RequestParam("fileImage") MultipartFile fileImage, @PathVariable long id) {
        return chambreService.handleImageFileUpload(fileImage,id);
    }

    @GetMapping("/getImage/{fileName:.+}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
        ByteArrayResource resource = fileStorageService.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the media type based on your image type
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }


    @PostMapping("/addchambree")
    public Chambre addChambre(@RequestBody Chambre chambre) {

        return chambreService.addChambre(chambre);
    }


@GetMapping("/chambre/{id}")
public Chambre retrieveChambre(@PathVariable Long id) {

    return chambreService.getChambre(id);
}

    @GetMapping("/chambres")

    public List<Chambre> retrieveAllChambres() {

        return chambreService.getAllChambres();
    }

    @DeleteMapping("/chambre/{id}")
    public void deleteChambre(@PathVariable long id) {

        chambreService.deleteChambre(id);
    }

    @PutMapping("/chambre")
    public Chambre updateChambre(@RequestBody Chambre chambre) {

        return chambreService.updateChambre(chambre);
    }

//    @GetMapping("/nombreChambresParType")
//    public Map<TypeChambre, Long> getNombreChambresParType() {
//        List<Chambre> chambres = chambreService.getAllChambres();
//        return ChambreService.getNombreChambresParType(chambres);



    @GetMapping("/statistique/type")
    public Map<TypeChambre, Long> statistiqueNombreChambresParType() {
        return chambreService.calculerNombreChambresParType();
    }

    }

//    @GetMapping("/statistics")
//    public List<Object[]> getChambreStatistics() {
//        return chambreService.getChambreStatistics();
//    }


//    @GetMapping("/nombreChambresParType")
//    public Map<TypeChambre, Long> getStatistiqueNombreChambresParType() {
//        return chambreService.statistiqueNombreChambresParType();
//    }
