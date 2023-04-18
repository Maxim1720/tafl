package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "app")
public class App extends RunnableEntity {

}
