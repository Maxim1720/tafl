package kz.trankwilizator.tafl.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import kz.trankwilizator.tafl.entity.JwtToken;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50, min = 1)
    @Column(length = 50, nullable = false, unique = true, updatable = false)
    private String username;


    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "decimal(10,2)", nullable = false)
    private BigDecimal balance;

    @NotNull
    @Column(nullable = false)
    private Boolean enabled;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;


    @OneToMany(targetEntity = JwtToken.class,
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private Set<JwtToken> jwtTokens = new HashSet<>();
}
