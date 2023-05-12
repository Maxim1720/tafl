import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;


@Entity
@Table(name = "tariffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;
    
    @ManyToMany(mappedBy = "tariffs")
    private Set<Zone> zones = new HashSet<>();

    // Остальные поля и методы класса
}
