package GB.service;


import GB.entity.Product;
import GB.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product getById(Long id){
        return productRepo.getById(id);
    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepo.getAllProducts();
        products.sort(Comparator.comparingLong(Product::getId));
        return products;
    }

    public void saveProduct (Product product){
        productRepo.saveProduct(product);
    }

    public void removeProductById(Long id){
        productRepo.removeProduct(id);
    }


}
