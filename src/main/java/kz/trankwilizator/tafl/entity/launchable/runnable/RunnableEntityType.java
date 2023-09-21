package kz.trankwilizator.tafl.entity.launchable.runnable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "runnable_entity_type")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class RunnableEntityType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 75)
    private String name;

    @OneToMany(targetEntity = RunnableEntity.class, mappedBy = "type")
    private List<RunnableEntity> runnableEntities = new ArrayList<>();
}