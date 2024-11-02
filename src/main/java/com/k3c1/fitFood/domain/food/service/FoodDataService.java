package com.k3c1.fitFood.domain.food.service;

import com.k3c1.fitFood.domain.food.dto.FoodResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodDataService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper; // For JSON parsing

    public FoodDataService(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper; // Initialize ObjectMapper
    }

    public Mono<List<FoodResponseDto>> getFoodData(String foodName) {
        try {
            // Encode the service key and food name to handle special characters
            String serviceKey = "ttJoCYUHU/ggOyI6FBbhpmdWM2RC9nc1h/KKqoDZeJrk8akyIIrDieDl5zcZmlwWd3OWnNgQ0GCb2/t+D2v2qA==";
            String encodedServiceKey = URLEncoder.encode(serviceKey, StandardCharsets.UTF_8.toString());
            String encodedFoodName = URLEncoder.encode(foodName, StandardCharsets.UTF_8.toString());

            // Build the URI
            URI uri = new URI("https://apis.data.go.kr/1471000/FoodNtrCpntDbInfo01/getFoodNtrCpntDbInq01?" +
                    "serviceKey=" + encodedServiceKey +
                    "&pageNo=1" +
                    "&numOfRows=100" +
                    "&type=json" +
                    "&FOOD_NM_KR=" + encodedFoodName);

            // Use the constructed URI in the WebClient call
            return webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(String.class) // Expecting a JSON response
                    .map(this::parseFoodData) // Parse the food data response
                    .doOnError(e -> System.err.println("Error retrieving food data: " + e.getMessage())); // Error handling

        } catch (Exception e) {
            return Mono.error(new RuntimeException("Error creating request URI: " + e.getMessage()));
        }
    }

    // Method to parse the JSON response and calculate total calories
    private List<FoodResponseDto> parseFoodData(String jsonResponse) {
        List<FoodResponseDto> foodResponseList = new ArrayList<>();
        try {
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode items = rootNode.path("body").path("items");

            for (JsonNode item : items) {
                String foodName = item.path("FOOD_NM_KR").asText();
                double carbs = item.path("AMT_NUM6").asDouble(); // Carbohydrates
                double protein = item.path("AMT_NUM7").asDouble(); // Protein
                double fat = item.path("AMT_NUM8").asDouble(); // Fat
                String servingSize = item.path("SERVING_SIZE").asText(); // e.g., "100g" or "100mL"

                // Calculate total calories per 100g
                double totalCaloriesPer100g = calculateTotalCalories(carbs, protein, fat, servingSize);

                // Create FoodResponseDto and add to the list
                foodResponseList.add(new FoodResponseDto(foodName, totalCaloriesPer100g));
            }

        } catch (Exception e) {
            System.err.println("Error parsing food data: " + e.getMessage());
        }
        return foodResponseList;
    }

    // Method to calculate total calories based on serving size
    private double calculateTotalCalories(double carbs, double protein, double fat, String servingSize) {
        double servingSizeInG = convertServingSizeToG(servingSize);
        // Calculate calories
        double totalCalories = (carbs * 4 + protein * 4 + fat * 9);
        // Adjust based on serving size
        return (totalCalories * (100 / servingSizeInG)); // Calories per 100g
    }

    // Method to convert serving size to grams
    private double convertServingSizeToG(String servingSize) {
        if (servingSize.toLowerCase().contains("ml")) {
            return 100; // Assuming 100mL equals 100g for this example; adjust logic as necessary
        }
        // Add other cases for different serving size types if necessary
        return 100; // Default to 100g if unknown
    }
}