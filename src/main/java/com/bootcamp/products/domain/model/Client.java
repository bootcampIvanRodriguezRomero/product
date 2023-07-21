package com.bootcamp.products.domain.model;

import com.bootcamp.products.domain.model.product.Product;
import java.util.List;
import lombok.Data;
import reactor.core.publisher.Flux;

@Data
public class Client {
  private String id;
  private ClientType type;
  private List<Product> products;
}
