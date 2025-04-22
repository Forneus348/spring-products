package com.example.RPM9._0.controller;

import com.example.RPM9._0.repository.Product;
import com.example.RPM9._0.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // говорит что в этом классе есть эндпоинты
@RequestMapping(path = "api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "{id}")
    public Product findById(Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        productService.delete(id);
    }

    @PutMapping(path = "{id}")
    public void update(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String description
    ) {
        productService.update(id, name, price, description);
    }
}