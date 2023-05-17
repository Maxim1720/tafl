package kz.trankwilizator.tafl.entity.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="sales")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sale{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable=false)
  private Long id;

  @ManyToMany(targetEntity = Product.class)
  @ToString.Exclude
  private List<Product> products;
  
  @Column(nullable=false)
  private BigDecimal price;
  private String description;
  
  @Column(nullable=false)
  @CreationTimestamp
  private Date timestamp;
  
}
