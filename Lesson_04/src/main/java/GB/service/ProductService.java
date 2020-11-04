package GB.service;

import GB.entity.Product;

import java.util.List;

public interface ProductService {

    public Product getById(Long id);
    public List<Product> getAllProducts();
    public void saveProduct (Product product);
    public void removeProductById(Long id);
}
