package kz.trankwilizator.tafl.entity.launchable;

import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.launchable.runnable.RunnableEntityDetails;
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

    @Embedded
    private RunnableEntityDetails runnableEntityDetails;

    @CreationTimestamp
    @Column(nullable = false)
    private Date startTime;

    private Date endTime;
}
