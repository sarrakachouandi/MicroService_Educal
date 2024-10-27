package com.esprit.microservices.foyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Foyer")
@CrossOrigin(origins = "http://localhost:4200")
public class FoyerRestAPI {

    @GetMapping("")
    public ResponseEntity<String> welcome() {
        return new ResponseEntity<>("Welcome to the Foyer Micro-Service", HttpStatus.OK);
    }


    @Autowired
    private FoyerService foyerService;
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Foyer> createFoyer(@RequestBody Foyer Foyer) {
        return new ResponseEntity<>(foyerService.addFoyer(Foyer), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Foyer>> getFoyersByCapacities(
            @RequestParam(name = "lowCapacity") boolean lowCapacity,
            @RequestParam(name = "mediumCapacity") boolean mediumCapacity,
            @RequestParam(name = "highCapacity") boolean highCapacity) {

        List<Foyer> filteredFoyers = foyerService.getFoyersByCapacities(lowCapacity, mediumCapacity, highCapacity);

        return ResponseEntity.ok(filteredFoyers);
    }
    @GetMapping("/capacity/{capacity}")
    public List<Foyer> getFoyersByCapacity(@PathVariable long capacity) {
        return foyerService.findByCapacityFoyerLessThan(capacity);
    }
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Foyer> updateFoyer(@PathVariable(value = "id") long id,
                                             @RequestBody Foyer Foyer){
        return new ResponseEntity<>(foyerService.updateFoyer(id, Foyer),
                HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteFoyer(@PathVariable(value = "id") long id){
        return new ResponseEntity<>(foyerService.deleteFoyer(id), HttpStatus.OK);
    }
    @GetMapping(value = "/foyers", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Foyer>> getFoyers(){
        return new ResponseEntity<>(foyerService.getFoyer(), HttpStatus.OK);
    }

    @GetMapping("/foyer/{id}")
    Foyer retrievefoyer(@PathVariable Long id){

        return foyerService.getFoyer(id);
    }

}