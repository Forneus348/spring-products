package com.example.RPM9._0.controller;

import com.example.RPM9._0.repository.Product;
import com.example.RPM9._0.repository.ProductDto;
import com.example.RPM9._0.repository.ResponseServer;
import com.example.RPM9._0.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ResponseServer> findById(Long id) {
        ResponseServer response = productService.findById(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseServer(false, HttpStatus.NOT_FOUND, List.of("продукта с таким id не существует"), new Product()));
        } else {
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseServer> create(@RequestBody ProductDto productDto) {
        ResponseServer response = productService.create(productDto);
        return ResponseEntity.status(response.statusCode).body(response);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<ResponseServer> delete(@PathVariable(name = "id") Long id) {
        /*productService.delete(id);
        return ResponseEntity.status(response.statusCode).body(response);*/
        ResponseServer response = productService.delete(id);
        return ResponseEntity.status(response.statusCode).body(response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ResponseServer> update(
            @PathVariable Long id, ProductDto productDto
    ) {
        ResponseServer response = productService.update(id, productDto);
        return ResponseEntity.status(response.statusCode).body(response);
    }
}