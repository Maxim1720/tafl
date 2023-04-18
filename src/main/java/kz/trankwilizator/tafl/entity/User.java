package kz.trankwilizator.tafl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity(name = "user_account")
@Table(name = "user_account")
public class User implements Serializable {
    @ToString.Include
    @Id
    @Column(nullable = false)
    private Long id;


}
