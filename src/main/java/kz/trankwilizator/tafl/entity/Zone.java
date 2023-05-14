package kz.trankwilizator.tafl.entity;
import jakarta.persistence.*;

@Entity
@Table(name="zone")
public class Zone {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(nullable=false)
  Long id;
  
  @Column(nullable=false)
  String name;
}
