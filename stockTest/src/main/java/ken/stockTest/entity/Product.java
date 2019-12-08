package ken.stockTest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "imageSrc")
    private String imageSrc;

    @Column(name = "price")
    private Double price;

    @Column(name = "count")
    private Double count;

}
