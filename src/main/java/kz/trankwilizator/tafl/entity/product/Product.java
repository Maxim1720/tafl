package kz.trankwilizator.tafl.entity.product;

import jakarta.validation.constraints.*;
import lombok.*;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 75)
    @NotBlank
    @Column(name = "name", length = 75, nullable = false)
    private String name;

    @PositiveOrZero
    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
}
