package kz.trankwilizator.tafl.entity.role;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity(name = "role")
@Table(name = "role")
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 2, max= 75)
    @Pattern(regexp = "^([A-Z]+_?[A-Z])+$")
    @Column(length = 75, nullable = false)
    private String name;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "permissions_id", nullable = false))
    private Set<Permission> permissions;

}
