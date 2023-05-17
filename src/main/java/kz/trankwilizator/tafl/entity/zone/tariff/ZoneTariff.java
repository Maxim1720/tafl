package kz.trankwilizator.tafl.entity.zone.tariff;

import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.zone.Zone;
import lombok.*;

import java.math.BigDecimal;

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

    private BigDecimal price;
}
