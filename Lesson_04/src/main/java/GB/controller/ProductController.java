package GB.controller;


import GB.entity.Product;
import GB.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
                       @RequestParam(required = false, name = "min") Double minPrice,
                       @RequestParam(required = false, name = "max") Double maxPrice) {
        List<Product> products = productService.getAllProducts();
        if (minPrice != null) {
            products.removeIf(p -> p.getPrice() <= minPrice);
        }
        if(maxPrice != null){
            products.removeIf(p -> p.getPrice() >= maxPrice);
        }
        /*int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), products.size());*/
        Page<Product> pages = new PageImpl<>(products, PageRequest.of(0, 5),products.size());
        model.addAttribute("products", products);
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
}
