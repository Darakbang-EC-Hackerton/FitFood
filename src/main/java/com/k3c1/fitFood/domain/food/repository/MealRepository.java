package com.k3c1.fitFood.domain.food.repository;

import com.k3c1.fitFood.domain.food.entity.EatTime;
import com.k3c1.fitFood.domain.food.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByMealTime(EatTime mealTime);
}