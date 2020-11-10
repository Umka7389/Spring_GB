package com.gb.springmarket.spring.controller;


import com.gb.springmarket.spring.entity.Product;
import com.gb.springmarket.spring.service.ProductService;
import com.gb.springmarket.spring.utils.ProductFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;


    public ProductController() {
    }

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false, name = "p", defaultValue = "1") Integer pageNumber,
                       @RequestParam(required = false, name = "min") Double minPrice,
                       @RequestParam(required = false, name = "max") Double maxPrice) {
        ProductFilter productFilter =new ProductFilter(minPrice, maxPrice);
        Page<Product> products = productService.getAllProducts(productFilter.getSpec(), pageNumber - 1, 5 );
        model.addAttribute("products", products);
        model.addAttribute("filter", productFilter.getFilterDefinition());
        return "list";
    }


    @GetMapping("/new")
    public String getFormNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";
    }

    @PostMapping("/new")
    public String addNewProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/update")
    public String getFormForUpdateProduct(Model model,
                                          @RequestParam(name = "id") Long id) {
        model.addAttribute("updateProduct", productService.getById(id));
        return "update-product";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam(name = "id") Long id,
                                @RequestParam(name = "title") String title,
                                @RequestParam(name = "price") Double price) {
        productService.updateProduct(id, title, price);
        return "redirect:/products";
    }
}
