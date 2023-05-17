package kz.trankwilizator.tafl.entity.zone.tariff;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;
    private Date startOfAction;
    private Date endOfAction;
}
