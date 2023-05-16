package kz.trankwilizator.dao.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import kz.trankwilizator.entity.product.Category;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
  
}
