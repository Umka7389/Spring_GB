package GB.service;


import GB.entity.Product;
import GB.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

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
    @Transactional
    public Product getById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        products.sort(Comparator.comparingLong(Product::getId));
        return products;
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


}
