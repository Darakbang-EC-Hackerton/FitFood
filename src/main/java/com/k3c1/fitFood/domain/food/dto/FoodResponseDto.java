package com.k3c1.fitFood.domain.food.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoodResponseDto {
    private String foodName;  // The name of the food
    private double totalCalories; // Total calories per 100g
}