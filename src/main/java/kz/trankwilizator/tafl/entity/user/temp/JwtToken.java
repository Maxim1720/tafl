package kz.trankwilizator.tafl.entity.user.temp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@ToString
@Entity(name = "jwt_token")
@Table(name = "jwt_token")
public class JwtToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = TemporaryUser.class, cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private TemporaryUser temporaryUser;

    @Column(length = 250, unique = true)
    private String token;

    @CreationTimestamp
    @Column(nullable = false)
    private Date createdAt;

    private Date expiryAt;
}
