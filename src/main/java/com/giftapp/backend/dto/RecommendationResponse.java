package com.giftapp.backend.dto;

import lombok.Data;

@Data
public class RecommendationResponse {

    private Long id;
    private String name;
    private String category;
    private String colour;
    private double price;
    private int score;

    public RecommendationResponse(Long id,
                                  String name,
                                  String category,
                                  String colour,
                                  double price,
                                  int score) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.colour = colour;
        this.price = price;
        this.score = score;
    }
}