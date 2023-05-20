package kz.trankwilizator.tafl.entity.launchable.device;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.launchable.RunnableEntity;
import kz.trankwilizator.tafl.entity.zone.Zone;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.RepositoryDefinition;

import java.io.Serializable;

@Table(name = "device")
@Entity
@Getter
@Setter
public class Device extends RunnableEntity implements Serializable {


  @ManyToOne
  @JoinColumn(name = "zone_id", nullable = false)
  private Zone zone;

  @Column(unique = true, nullable = false)
  private Long number;

  @ManyToOne
  @JoinColumn(name = "device_status_id", nullable = false)
  private DeviceStatus deviceStatus;
}
