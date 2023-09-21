package kz.trankwilizator.tafl.entity.schedule;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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

    @Past
    @NotNull
    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime startTime;

    @Future
    @NotNull
    @Column(nullable = false, columnDefinition = "TIME")
    private LocalTime endTime;
}
