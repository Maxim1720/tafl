package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbsUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false)
    private String username;


    @Column(columnDefinition = "decimal(10,2)", nullable = false)
    private Double balance = 0.0;

    private Boolean enabled;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;
}
