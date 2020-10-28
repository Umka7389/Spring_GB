package GB.repository;


import GB.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private Map<Long, Product> repo = new HashMap<>();
    private long ind = 0;

    {
        repo.put(++ind, new Product(ind, "Milk",60.0));
        repo.put(++ind, new Product(ind, "Bread",45.0));
        repo.put(++ind, new Product(ind, "Sugar",120.0));
        repo.put(++ind, new Product(ind, "Cheese",250.0));
        repo.put(++ind, new Product(ind, "Meat",500.0));
    }

    public Product getById (Long id){
        return repo.get(id);
    }

    public List <Product> getAllProducts (){
        return new ArrayList<>(repo.values());
    }

    public void saveProduct(Product product){
        if (product.getId() == null){
            product.setId(++ind);
        }
        repo.put(product.getId(), product);
    }

    public void removeProduct (Long id){
        repo.remove(id);
    }
}
