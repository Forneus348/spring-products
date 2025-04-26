package com.example.RPM9._0.service;

import com.example.RPM9._0.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

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
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());

        Product savedProduct = productRepository.save(product);
        return new ResponseServer(true, HttpStatus.OK, List.of("Продукт успешно создан"), savedProduct);
    }

    @Transactional
    public ResponseServer update(Long id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, "продукт с таким id: " + id + " не найден"));

        if (productDto.getName() != null) {
            product.setName(productDto.getName() );
        }

        if (productDto.getPrice()  != null) {
            product.setPrice(productDto.getPrice());
        }

        if (productDto.getDescription() != null) {
            product.setDescription(productDto.getDescription());
        }

        return new ResponseServer(true, HttpStatus.OK, List.of("Продукт успешно обновлён"), product);
    }

    public ResponseServer delete(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() -> new ProductNotFoundException(HttpStatus.NOT_FOUND, "продукт с таким id: " + id + " не найден"));

       /* Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new IllegalStateException("продукта с id: " + id + " не существует");
        }
        productRepository.deleteById(id);*/

        productRepository.deleteById(id);
        return new ResponseServer(true, HttpStatus.OK, List.of("Продукт успешно удалён"), new Product());

    }
}
