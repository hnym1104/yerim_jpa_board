package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IndexColumn;
import yerim.board.domain.Category;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;
    private Long price;
    private Long stockQuantity;

    //@Column(name = "item_type")
    //private String item_type;

}
