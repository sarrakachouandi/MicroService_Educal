package com.example.restaurantms.services;

import com.example.restaurantms.entities.Restaurant;
import com.example.restaurantms.repositories.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements IRestaurantService {
    private RestaurantRepository restaurantRepository;
    private FileStorageService fileStorageService;

    @Override
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(int id, Restaurant newRestaurant) {
        if (restaurantRepository.findById(id).isPresent()) {
            Restaurant existingRestaurant = restaurantRepository.findById(id).get();
            existingRestaurant.setNomRestaurant(newRestaurant.getNomRestaurant());
            existingRestaurant.setMenu(newRestaurant.getMenu());
            existingRestaurant.setImageUrl(newRestaurant.getImageUrl());
            existingRestaurant.setSpecialite(newRestaurant.getSpecialite());

            return restaurantRepository.save(existingRestaurant);
        } else
            return null;
    }


    public Restaurant getRestaurantId(int id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteRestaurant(int id) {
        if (restaurantRepository.findById(id).isPresent()) {
            restaurantRepository.deleteById(id);
            return "restaurant supprimé";
        } else
            return "restaurant non supprimé";
    }
    @Override
    public Restaurant handleImageFileUpload(MultipartFile fileImage, int id) {
        if (fileImage.isEmpty()) {
            return null;
        }
        String fileName = fileStorageService.storeFile(fileImage);
        Restaurant chambre = restaurantRepository.findById(id).orElse(null);
        chambre.setImageUrl(fileName);
        return restaurantRepository.save(chambre);
    }
}

