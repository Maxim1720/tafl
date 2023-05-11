package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import kz.trankwilizator.tafl.entity.Zone;

@Table(name = "computer")
@Entity
@Getter
@Setter
public class Computer extends RunnableEntity {
  @JoinColumn(nullable=false)
  private Zone zone;
}
