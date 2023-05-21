package kz.trankwilizator.tafl.entity.launchable.runnable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "runnable_entity_type")
public class RunnableEntityType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(targetEntity = RunnableEntity.class, mappedBy = "type")
    private List<RunnableEntity> runnableEntities = new ArrayList<>();
}