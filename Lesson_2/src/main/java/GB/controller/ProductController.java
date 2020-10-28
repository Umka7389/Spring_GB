package GB.controller;


import GB.entity.Product;
import GB.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping ("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("/new")
    public String getFormNewProduct(Model model){
        model.addAttribute("product", new Product());
        return "new-product";
    }
    @RequestMapping (value = "/new", method = RequestMethod.POST)
    public String addNewProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/products";
    }
}
