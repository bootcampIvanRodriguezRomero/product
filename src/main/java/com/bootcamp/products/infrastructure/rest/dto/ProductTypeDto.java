package com.bootcamp.products.infrastructure.rest.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class ProductTypeDto {
    @JsonProperty("identification")
    @Id
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
}
