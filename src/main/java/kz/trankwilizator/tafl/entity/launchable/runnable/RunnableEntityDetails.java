package kz.trankwilizator.tafl.entity.launchable.runnable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class RunnableEntityDetails {

    @Column(name = "runnable_entity_id")
    private Long runnableEntityId;

    @Column(name = "runnable_entity_type")
    private String runnableEntityType;

}
