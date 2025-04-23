package com.example.RPM9._0.service;

import com.example.RPM9._0.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseServer findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, "продукт с таким id: " + id + " не найден"));
        return new ResponseServer(true, HttpStatus.OK, List.of("нет ошибок"), product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public ResponseServer create(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findByName(productDto.getName());
//        if (optionalProduct.isPresent()) {
//            throw new IllegalStateException("продукт с таким name существует");
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException(HttpStatus.CONFLICT, "продукт с таким name " + productDto.getName() + " существует"));
//        }


        Long maxId = productRepository.findAll().stream()
                .map(Product::getId)
                .max(Long::compareTo).orElse(0L) + 1;

        Product newProduct = new Product(productDto.getName(), productDto.getPrice(), productDto.getDescription());
        return new ResponseServer(true, HttpStatus.CREATED, List.of("нет ошибок"), productRepository.save(newProduct));
    }

    @Transactional
    public void update(Long id, String name, Double price, String description) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new IllegalStateException("продукта с id: " + id + " не существует");
        }
        Product product = optionalProduct.get();

        if (name != null && !name.equals(product.getName())) {
            Optional<Product> foundByEmail = productRepository.findByName(name);
            if (foundByEmail.isPresent()) {
                throw new IllegalStateException("продукт с таким названием существует");
            }
            product.setName(name);
        }

        if (price != null && !price.equals(product.getPrice())) {
            product.setPrice(price);
        }

        if (description != null && !description.equals(product.getDescription())) {
            product.setDescription(description);
        }
    }

    public void delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new IllegalStateException("продукта с id: " + id + " не существует");
        }
        productRepository.deleteById(id);
    }
}
