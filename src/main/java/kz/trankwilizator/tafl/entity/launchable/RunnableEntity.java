package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;


//@MappedSuperclass
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RunnableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
