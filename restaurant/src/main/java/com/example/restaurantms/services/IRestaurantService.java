package com.example.restaurantms.services;

import com.example.restaurantms.entities.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRestaurantService {
     Restaurant handleImageFileUpload(MultipartFile fileImage, int id) ;

        Restaurant getRestaurantId(int id);
    List<Restaurant> getAll();

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant updateRestaurant(int id, Restaurant newRestaurant);

    String deleteRestaurant(int id);

}