package com.k3c1.fitFood.domain.food.dto;


import com.k3c1.fitFood.domain.food.entity.EatTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealDto {
    private Long id;
    private String name;
    private EatTime mealTime;
    private int calories; // Total calories for this meal
}