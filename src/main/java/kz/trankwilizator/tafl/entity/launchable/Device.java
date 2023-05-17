package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.zone.Zone;
import lombok.Getter;
import lombok.Setter;

@Table(name = "devices")
@Entity
@Getter
@Setter
public class Device extends RunnableEntity {
  @ManyToOne
  @JoinColumn(name = "zone_id")
  private Zone zone;
}
