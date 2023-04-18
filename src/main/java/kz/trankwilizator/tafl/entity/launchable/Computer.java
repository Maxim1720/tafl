package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "computer")
@Entity
@Getter
@Setter
public class Computer extends RunnableEntity {

}
