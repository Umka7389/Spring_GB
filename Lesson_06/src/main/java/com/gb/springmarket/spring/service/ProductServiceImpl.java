package com.gb.springmarket.spring.service;


import com.gb.springmarket.spring.entity.Product;
import com.gb.springmarket.spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Long id) {
        return productRepo.findById(id).orElse(null);
    }


    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Specification<Product> spec, int page, int size ) {
        return productRepo.findAll(spec, PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        productRepo.save(product);
    }


    @Override
    @Transactional
    public void removeProductById(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, String title, Double price) {
        Product product = productRepo.findById(id).orElse(null);
        product.setTitle(title);
        product.setPrice(price);
        productRepo.save(product);
    }


}
