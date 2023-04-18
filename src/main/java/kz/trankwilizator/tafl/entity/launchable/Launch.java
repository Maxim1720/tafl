package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "launch_count")
@Getter
@Setter
public class Launch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = RunnableEntity.class, orphanRemoval = true)
    private RunnableEntity runnableEntity;

    @CreationTimestamp
    @Column(nullable = false)
    private Date startTime;

    private Date endTime;
}
