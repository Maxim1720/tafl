import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zone_tariffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZoneTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    // Остальные поля и методы класса
}
