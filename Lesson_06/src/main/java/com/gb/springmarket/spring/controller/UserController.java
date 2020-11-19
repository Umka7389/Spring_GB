package com.gb.springmarket.spring.controller;


import com.gb.springmarket.spring.entity.User;
import com.gb.springmarket.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false, name = "p", defaultValue = "1") Integer pageNumber) {
        Page<User> users = userService.getAllUsers(pageNumber - 1, 20);
        model.addAttribute("users", users);
        return "users";
    }


    /*@GetMapping("/new")
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
    }*/
}
