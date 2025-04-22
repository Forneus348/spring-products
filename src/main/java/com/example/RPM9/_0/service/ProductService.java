package com.example.RPM9._0.service;

import com.example.RPM9._0.repository.Product;
import com.example.RPM9._0.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.orElseThrow(() ->  new IllegalStateException("продукт с таким id:  " + id + " не найден"));
        return product;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent()) {
            throw new IllegalStateException("продукт с таким name существует");
        }
        return productRepository.save(product);
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
