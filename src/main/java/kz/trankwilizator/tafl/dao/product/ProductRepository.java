package kz.trankwilizator.tafl.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import kz.trankwilizator.tafl.entity.product.Product;

@RepositoryRestResource(path="products")
public interface ProductRepository extends JpaRepository<Product, Integer> {
  
}
