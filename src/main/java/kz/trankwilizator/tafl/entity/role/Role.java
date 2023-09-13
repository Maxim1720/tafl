package kz.trankwilizator.tafl.entity.role;

import jakarta.persistence.*;
import kz.trankwilizator.tafl.entity.user.PermanentUser;
import kz.trankwilizator.tafl.entity.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "role")
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 75, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "permissions_id", nullable = false))
    private Set<Permission> permissions;

}
