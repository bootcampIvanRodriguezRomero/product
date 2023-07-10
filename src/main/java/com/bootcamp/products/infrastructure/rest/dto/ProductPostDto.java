package com.bootcamp.products.infrastructure.rest.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductPostDto {
    @NotBlank(message = "Type is required")
    private String type;
}
