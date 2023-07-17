package com.bootcamp.products.infrastructure.rest.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class ProductPostDto {
    @NotBlank(message = "Type is required")
    private String type;
    @NotBlank(message = "Amount is required")
    private double amount;
}
