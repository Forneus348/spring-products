package com.example.RPM9._0.controller;

import com.example.RPM9._0.repository.Product;
import com.example.RPM9._0.repository.ProductDto;
import com.example.RPM9._0.repository.ProductNotFoundException;
import com.example.RPM9._0.repository.ResponseServer;
import com.example.RPM9._0.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        if (response.isSuccess){
            return ResponseEntity.ok(response);
//        return productService.create(productDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseServer(false, HttpStatus.CONFLICT, List.of("продукт с таким name " + productDto.getName() + " существует"), new Product()));

        }
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