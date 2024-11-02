package com.k3c1.fitFood.domain.food.service;

import com.k3c1.fitFood.domain.food.dto.MealDto;
import com.k3c1.fitFood.domain.food.entity.Meal;
import com.k3c1.fitFood.domain.food.repository.MealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MealService {

    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    // Register a meal
    @Transactional
    public MealDto registerMeal(MealDto mealDto) {
        Meal meal = new Meal();
        meal.setName(mealDto.getName());
        meal.setMealTime(mealDto.getMealTime());
        meal.setCalories(mealDto.getCalories());
        mealRepository.save(meal);
        mealDto.setId(meal.getId());
        return mealDto;
    }

    // Edit a meal
    @Transactional
    public MealDto editMeal(Long id, MealDto mealDto) {
        Meal meal = mealRepository.findById(id).orElseThrow(() -> new RuntimeException("Meal not found"));
        meal.setName(mealDto.getName());
        meal.setMealTime(mealDto.getMealTime());
        meal.setCalories(mealDto.getCalories());
        mealRepository.save(meal);
        return mealDto;
    }

    // Delete a meal
    @Transactional
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    // Get total calories for today
    public int getTotalCalories() {
        return mealRepository.findAll().stream().mapToInt(Meal::getCalories).sum(); // Assuming all meals are for today
    }

    // Get meal recommendations based on remaining calories
    public List<MealDto> getMealRecommendations(int remainingCalories) {
        // Logic for getting recommendations based on remainingCalories
        // This is a placeholder for the actual implementation
        return mealRepository.findAll().stream()
                .filter(meal -> meal.getCalories() <= remainingCalories)
                .map(meal -> {
                    MealDto dto = new MealDto();
                    dto.setId(meal.getId());
                    dto.setName(meal.getName());
                    dto.setMealTime(meal.getMealTime());
                    dto.setCalories(meal.getCalories());
                    return dto;
                }).toList();
    }
}