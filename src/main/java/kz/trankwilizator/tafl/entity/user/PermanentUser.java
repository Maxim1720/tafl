package kz.trankwilizator.tafl.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import kz.trankwilizator.tafl.entity.role.Role;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@Entity(name = "user_profile")
@Table(name = "user_profile")
@NoArgsConstructor
@AllArgsConstructor
public class PermanentUser extends User {

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 75)
    @Column(length = 75, nullable = false)
    private String firstname;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 75)
    @Column(length = 75, nullable = false)
    private String lastname;

    @Column(length = 75)
    private String secondName;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 75)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Column(length = 256, nullable = false, unique = true, updatable = false)
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 60, max = 60)
    @JsonIgnore
    @Column(length = 60, nullable = false)
    private char[] password;

    @NotNull
    @PositiveOrZero()
    @DecimalMin(value = "0")
    @DecimalMax(value = "1")
    @Column(columnDefinition = "decimal(1,2)", nullable = false)
    private Double discount;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedAt;

    @NotNull
    @ToString.Exclude
    @ManyToOne(optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
