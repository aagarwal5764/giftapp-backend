package com.giftapp.backend.dto;

import lombok.Data;

@Data
public class RecommendationRequest {

    private int age;
    private String gender;
    private String occasion;
    private Double budget;
    private String colour;
}