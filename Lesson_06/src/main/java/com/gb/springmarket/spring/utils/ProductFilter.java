package com.gb.springmarket.spring.utils;


import com.gb.springmarket.spring.entity.Product;
import com.gb.springmarket.spring.repository.specifiacations.ProductSpecifications;
import org.springframework.data.jpa.domain.Specification;


public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Double minPrice, Double maxPrice) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);

        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEquals(minPrice));
            filterDefinitionBuilder.append("&min=").append(minPrice);
        }

        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
            filterDefinitionBuilder.append("&max=").append(maxPrice);
        }

        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Product> getSpec() {
        return spec;
    }

    public void setSpec(Specification<Product> spec) {
        this.spec = spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }

    public void setFilterDefinition(String filterDefinition) {
        this.filterDefinition = filterDefinition;
    }
}
