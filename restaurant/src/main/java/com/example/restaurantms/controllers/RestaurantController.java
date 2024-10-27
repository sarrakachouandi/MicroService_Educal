package com.example.restaurantms.controllers;


import com.example.restaurantms.QRCode.QRCodeGenerator;
import com.example.restaurantms.entities.Restaurant;
import com.example.restaurantms.repositories.RestaurantRepository;
import com.example.restaurantms.services.FileStorageService;
import com.example.restaurantms.services.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:4200")

public class RestaurantController {
    @Autowired
    FileStorageService fileStorageService;

    private static final String QR_CODE_IMAGE_DIRECTORY = "./src/main/resources/static/img/";
    @PostMapping("/uploadImage/{id}")
    public Restaurant handleImageFileUpload(@RequestParam("fileImage") MultipartFile fileImage, @PathVariable int id) {
        return restaurantService.handleImageFileUpload(fileImage,id);
    }
    @GetMapping("/getImage/{fileName:.+}")
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable String fileName) {
        ByteArrayResource resource = fileStorageService.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the media type based on your image type
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }

    @Autowired
    RestaurantRepository restaurantRepository;

    @GetMapping("/qrCode/{id}")
    public ResponseEntity<ByteArrayResource> getQRCode(@PathVariable(value = "id") int id) {
        Restaurant r = restaurantRepository.findById(id).orElse(null);
        String phrase = "*** Details Restaurant " +r.getNomRestaurant()+" ***  Specialite Restaurant : "+r.getSpecialite()+
                " Plat du jour : "+r.getMenu() ;
        byte[] image = new byte[0];
        try {
            image = QRCodeGenerator.getQRCodeImage(phrase, 250, 250);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        ByteArrayResource resource = new ByteArrayResource(image);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=QRCode.jpg");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.IMAGE_JPEG)
                .contentLength(image.length)
                .body(resource);
    }



   /* @GetMapping("/qrCode")
    public String getQRCode(Model model) {
        String phrase = "hello welcome to my qrCode"; // Set the desired phrase here
        byte[] image = new byte[0];
        try {
            image = QRCodeGenerator.getQRCodeImage(phrase, 250, 250);
            String uniqueID = UUID.randomUUID().toString();

            String uniqueFileName = "QRCode_" + uniqueID;

            String imagePath = QR_CODE_IMAGE_DIRECTORY + uniqueFileName + ".png";
            QRCodeGenerator.generateQRCodeImage(phrase, 250, 250, imagePath);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        String qrcode = Base64.getEncoder().encodeToString(image);
        model.addAttribute("phrase", phrase);
        model.addAttribute("qrcode", qrcode);
        return "qrcode";
    }*/




    @RequestMapping("/hello")
    public String sayHello() {
        String title = "Hello, i'm the restaurant Micro Service";
        System.out.println(title);
        return title;
    }

    @Autowired
    private RestaurantServiceImpl restaurantService;

    @GetMapping
    public List<Restaurant> getListCandid() {
        return restaurantService.getAll();
    }


    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> fetchRestaurantById (@PathVariable(value = "id") int id){
        return new ResponseEntity<>(restaurantService.getRestaurantId(id), HttpStatus.OK);

    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        return new ResponseEntity<>(restaurantService.addRestaurant(restaurant), HttpStatus.OK);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable(value = "id") int id,
                                                       @RequestBody Restaurant restaurant){
        return new ResponseEntity<>(restaurantService.updateRestaurant(id, restaurant), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteRestaurant(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(restaurantService.deleteRestaurant(id), HttpStatus.OK);
    }

}
