package kz.trankwilizator.tafl.entity.product;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable=false)
    private Long id;

    @Column(name = "name", nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;
}
