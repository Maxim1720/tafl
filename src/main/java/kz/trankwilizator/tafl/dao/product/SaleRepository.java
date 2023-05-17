package kz.trankwilizator.tafl.dao.product;

import kz.trankwilizator.tafl.entity.product.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "sales")
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
