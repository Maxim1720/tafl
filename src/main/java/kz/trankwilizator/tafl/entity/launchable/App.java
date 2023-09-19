package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
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

}
