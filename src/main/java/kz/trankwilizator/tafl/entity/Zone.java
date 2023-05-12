import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "zones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false)
    private int id;

    @Column(nullable=false)
    private String name;
    private String description;
}
