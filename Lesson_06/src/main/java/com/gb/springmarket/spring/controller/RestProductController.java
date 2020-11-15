package com.gb.springmarket.spring.controller;


import com.gb.springmarket.spring.entity.Product;
import com.gb.springmarket.spring.service.ProductService;
import com.gb.springmarket.spring.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class RestProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false, name = "p", defaultValue = "1") Integer pageNumber,
                                        @RequestParam(required = false, name = "min") Double minPrice,
                                        @RequestParam(required = false, name = "max") Double maxPrice) {
        ProductFilter productFilter = new ProductFilter(minPrice, maxPrice);
        return productService.getAllProducts(productFilter.getSpec(), pageNumber, 5).getContent();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product p) {
        return productService.saveProduct(p);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product p) {
        return productService.updateProduct(p.getId(), p.getTitle(), p.getPrice());
    }

    @DeleteMapping
    public void deleteAll() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.removeProductById(id);
    }

}
