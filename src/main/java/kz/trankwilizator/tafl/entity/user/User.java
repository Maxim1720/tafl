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


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DynamicUpdate
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Setter
    @NotBlank
    @Column(nullable = false, unique = true, updatable = false)
    protected String username;

    @Getter
    @Setter
    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "decimal(10,2)", nullable = false)
    private BigDecimal balance;

    @Getter
    @Setter
    @NotNull
    @Column(nullable = false)
    private Boolean enabled;

    @Getter
    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;


    @Getter
    @Setter
    @OneToMany(targetEntity = JwtToken.class,
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private Set<JwtToken> jwtTokens = new HashSet<>();
}
