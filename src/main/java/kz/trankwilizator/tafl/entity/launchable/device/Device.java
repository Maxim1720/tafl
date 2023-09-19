package kz.trankwilizator.tafl.entity.launchable.device;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntity;
import kz.trankwilizator.tafl.entity.zone.Zone;
import lombok.*;

import java.io.Serializable;

@Table(name = "device")
@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Device extends RunnableEntity implements Serializable {


  @NotNull
  @ManyToOne
  @JoinColumn(name = "zone_id", nullable = false)
  private Zone zone;

  @Positive
  @NotNull
  @Column(unique = true, nullable = false)
  private Long number;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "device_status_id", nullable = false)
  private DeviceStatus deviceStatus;
}
