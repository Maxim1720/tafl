package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "app")
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class App extends RunnableEntity {

    @NotBlank
    @Size(min = 1, max = 75)
    @Column(length = 75, nullable = false)
    private String name;
}
