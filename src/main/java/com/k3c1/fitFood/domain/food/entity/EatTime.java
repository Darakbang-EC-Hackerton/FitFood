package com.k3c1.fitFood.domain.food.entity;

import lombok.Getter;

@Getter
public enum EatTime {
    BREAKFAST(1, "Breakfast"),
    LUNCH(2, "Lunch"),
    DINNER(3, "Dinner"),
    SNACK(4, "Snack");

    private final int value;
    private final String description;


    EatTime(int value, String description) {
        this.value = value;
        this.description = description;
    }
}