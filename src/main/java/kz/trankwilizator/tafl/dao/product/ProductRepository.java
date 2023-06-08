package kz.trankwilizator.tafl.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import kz.trankwilizator.tafl.entity.product.Product;
import org.springframework.security.access.annotation.Secured;

@Secured({"MODERATOR", "CASHIER", "ADMIN"})
@RepositoryRestResource(path="products")
public interface ProductRepository extends JpaRepository<Product, Long> {
}
