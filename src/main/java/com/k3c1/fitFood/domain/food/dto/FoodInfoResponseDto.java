package com.k3c1.fitFood.domain.food.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;


@Getter
public class FoodInfoResponseDto {
    @JsonProperty("response")
    private Response response;

    // Getters and Setters

    public static class Response {
        @JsonProperty("header")
        private Header header;

        @JsonProperty("body")
        private Body body;

        // Getters and Setters
    }

    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;

        // Getters and Setters
    }

    public static class Body {
        @JsonProperty("items")
        private List<Item> items;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("pageNo")
        private int pageNo;

        // Getters and Setters
    }

    public static class Item {
        @JsonProperty("foodName")
        private String foodName;

        @JsonProperty("calories")
        private String calories;

        // Add other fields according to the API response

        // Getters and Setters
    }
}