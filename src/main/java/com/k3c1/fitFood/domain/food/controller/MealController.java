package com.k3c1.fitFood.domain.food.controller;

import com.k3c1.fitFood.domain.food.dto.MealDto;
import com.k3c1.fitFood.domain.food.entity.EatTime;
import com.k3c1.fitFood.domain.food.service.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    // GET total calories consumed today
    @GetMapping("/total_calorie")
    public ResponseEntity<Integer> getTotalCalories() {
        int totalCalories = mealService.getTotalCalories();
        return ResponseEntity.ok(totalCalories);
    }

    // GET meal recommendations based on remaining calories
    @GetMapping("/{remainingCalories}")
    public ResponseEntity<List<MealDto>> getMealRecommendations(@PathVariable int remainingCalories) {
        List<MealDto> recommendations = mealService.getMealRecommendations(remainingCalories);
        return ResponseEntity.ok(recommendations);
    }

    // POST to register a meal
    @PostMapping("/{mealType}")
    public ResponseEntity<MealDto> registerMeal(@PathVariable String mealType, @RequestBody MealDto mealDto) {
        mealDto.setMealTime(EatTime.valueOf(mealType.toUpperCase())); // Convert to Enum
        MealDto createdMeal = mealService.registerMeal(mealDto);
        return ResponseEntity.status(201).body(createdMeal);
    }

    // PUT to edit a meal
    @PutMapping("/{id}")
    public ResponseEntity<MealDto> editMeal(@PathVariable Long id, @RequestBody MealDto mealDto) {
        MealDto updatedMeal = mealService.editMeal(id, mealDto);
        return ResponseEntity.ok(updatedMeal);
    }

    // DELETE a meal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.noContent().build();
    }
}