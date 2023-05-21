package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app")
public class App extends RunnableEntity {

}
