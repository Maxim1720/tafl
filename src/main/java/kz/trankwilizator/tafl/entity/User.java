package kz.trankwilizator.tafl.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@Entity(name = "user_account")
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 75)
    private String firstname;

    @Column(length = 75)
    private String lastname;

    @Column(length = 75)
    private String secondName;

    @Column(length = 120)
    private String username;

    private String email;

    @CreationTimestamp
    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Column(columnDefinition = "decimal(10,2)")
    private Double balance;

    @Column(length = 200)
    private String password;

    @Column(columnDefinition = "decimal(10,2)")
    private Float discount;


}