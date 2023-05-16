package kz.trankwilizator.tafl.entity.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="sales)
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
  
  @ManyToMany(nullable=false)
  private List<Product> products;
  
  @Column(nullable=false)
  private BigDecimal price;
  private String description;
  
  @Column(nullable=false)
  @CreationTimestamp
  private Date timestamp;
  
}
