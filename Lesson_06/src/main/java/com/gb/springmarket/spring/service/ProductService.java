package com.gb.springmarket.spring.service;


import com.gb.springmarket.spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface ProductService {

    public Product getById(Long id);

    public Page<Product> getAllProducts(Specification<Product> spec, int page, int size );

    public void saveProduct(Product product);

    public void removeProductById(Long id);

    public void updateProduct (Long id, String title, Double price);
}
