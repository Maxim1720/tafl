package kz.trankwilizator.tafl.entity.zone.tariff;


import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.schedule.Schedule;
import lombok.*;

@Entity
@Table(name = "tariffs")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Schedule schedule;
}
