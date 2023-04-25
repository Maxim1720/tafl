package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import kz.trankwilizator.tafl.entity.JwtToken;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;


    @DecimalMin("0")
    @Column(columnDefinition = "decimal(10,2)", nullable = false)
    private Double balance = 0.0;

    @Column(nullable = false)
    private Boolean enabled = true;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;


    @OneToMany(targetEntity = JwtToken.class, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true/*, fetch = FetchType.EAGER*/)
    private Set<JwtToken> jwtTokens = new HashSet<>();
}
