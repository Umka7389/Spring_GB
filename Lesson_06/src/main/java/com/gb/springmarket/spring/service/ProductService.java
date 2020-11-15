package com.gb.springmarket.spring.service;


import com.gb.springmarket.spring.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface ProductService {

    public Product getById(Long id);

    public Page<Product> getAllProducts(Specification<Product> spec, int page, int size );

    public Product saveProduct(Product product);

    public void removeProductById(Long id);

    public Product updateProduct (Long id, String title, Double price);

    void deleteAll();
}
