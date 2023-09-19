package kz.trankwilizator.tafl.entity;

import jakarta.persistence.*;
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

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 250, unique = true, nullable = false)
    private String token;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;

    private Date expiryAt;
}
