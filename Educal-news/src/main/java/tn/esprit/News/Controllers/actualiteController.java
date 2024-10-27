package tn.esprit.News.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.News.Entities.Actualite;
import tn.esprit.News.Services.IActualiteService;

import java.util.List;
import java.util.Set;

//@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/news")
public class actualiteController {
    IActualiteService actualiteService;

    @PostMapping("/addActualite")
    Actualite addActualite(@RequestBody Actualite actualite){
        return actualiteService.addActualite(actualite);
    }

    @GetMapping("/getOneActualite/{id}")
    Actualite getActualite(@PathVariable Long id){
        return actualiteService.getActualite(id);
    }

    @GetMapping
    List<Actualite> getAllActualites(){
        return actualiteService.getAllActualites();
    }

    @DeleteMapping("/deleteActualite/{id}")
    void deleteClubById(@PathVariable Long id){
        this.actualiteService.deleteActualiteById(id);
    }

    @PutMapping("/updateActualite")
    Actualite updateActualite(@RequestBody Actualite actualite){
        return  this.actualiteService.updateActualite(actualite);
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
    @PostMapping("/uploadImage/{id}")
    public Actualite handleImageFileUpload(@RequestParam("fileImage") MultipartFile fileImage, @PathVariable long id) {
        return actualiteService.handleImageFileUpload(fileImage,id);
    }


    /*****************************************/

    @PostMapping("/shareFb/{id}")
    public String shareFb(@PathVariable Long id){
        return actualiteService.shareFb(id);
    }

}
