package com.bootcamp.products.infrastructure.rest.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
@Data
public class ClientDto {
    @JsonProperty("identification")
    @Id
    private String id;
    private String documentType;
    private String documentNumber;
    private String fullName;
    private String email;
    private String type;
}
