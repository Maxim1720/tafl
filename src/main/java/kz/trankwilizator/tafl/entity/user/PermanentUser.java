package kz.trankwilizator.tafl.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import kz.trankwilizator.tafl.entity.role.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "user_profile")
@Table(name = "user_profile")
@DiscriminatorValue("permanent")
public class PermanentUser extends User {

    @Column(length = 75, nullable = false)
    private String firstname;

    @Column(length = 75, nullable = false)
    private String lastname;

    @Column(length = 75)
    private String secondName;

    @Column(length = 256, nullable = false)
    private String email;

    @JsonIgnore
    @Column(length = 200, nullable = false)
    private String password;

    @DecimalMin(value = "0")
    @DecimalMax(value = "1")
    @Column(columnDefinition = "decimal(1,2)", nullable = false)
    private Double discount = 0.0;

    @UpdateTimestamp
    @Column(nullable = false)
    @LastModifiedDate
    @LastModifiedBy
    private Date updatedAt;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

}
