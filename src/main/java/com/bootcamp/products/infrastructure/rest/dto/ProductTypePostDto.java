package com.bootcamp.products.infrastructure.rest.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductTypePostDto {
    @NotBlank(message = "Name is required")
    private String name;
}
