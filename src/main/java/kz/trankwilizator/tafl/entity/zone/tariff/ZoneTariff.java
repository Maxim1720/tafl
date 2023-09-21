package kz.trankwilizator.tafl.entity.zone.tariff;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kz.trankwilizator.tafl.entity.zone.Zone;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "zone_tariffs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ZoneTariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal price;
}
