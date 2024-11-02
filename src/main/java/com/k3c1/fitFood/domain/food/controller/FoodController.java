package com.k3c1.fitFood.domain.food.controller;

import com.k3c1.fitFood.domain.food.dto.FoodResponseDto;
import com.k3c1.fitFood.domain.food.service.FoodDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodDataService foodDataService;

    public FoodController(FoodDataService foodDataService) {
        this.foodDataService = foodDataService;
    }

    @GetMapping("/calories")
    public Mono<List<FoodResponseDto>> getFoodCalories(@RequestParam String foodName) {
        // Call the service to get food data and return it as a Mono of a list of FoodResponseDto
        return foodDataService.getFoodData(foodName);
    }


}