package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.SellStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int stockQuantity;
    private Long buyPrice;   // 상품 개당 가격
    private String whereToBuy;   // 구매처
    private LocalDateTime buyTime;

    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Item() {
    }

    /*public Long totalPrice() {
        return stockQuantity * buyPrice;   // 총 구매 가격
    }*/

}
