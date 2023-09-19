package kz.trankwilizator.tafl.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kz.trankwilizator.tafl.entity.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "jwt_token")
@Table(name = "jwt_token")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(min = 5, max = 250)
    @NotBlank
    @Column(length = 250, unique = true, nullable = false)
    private String token;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;

    @NotNull
    @Future
    private Date expiryAt;
}
