package kz.trankwilizator.tafl.entity.schedule;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime endTime;
}
