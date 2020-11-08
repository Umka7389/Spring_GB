package GB.repository;

import GB.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findAllByPriceGreaterThanEqual(Double minPrice, Pageable pageable);

    Page<Product> findAllByPriceLessThanEqual(Double maxPrice, Pageable pageable);

    Page<Product> findAllByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

}
