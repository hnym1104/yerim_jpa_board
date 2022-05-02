package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.SellStatus;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate buyTime;   // 구매 시간

    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Item() {
    }

    public Item(String name, int stockQuantity, Long buyPrice, String whereToBuy, LocalDate buyTime, SellStatus sellStatus, Category category) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.buyPrice = buyPrice;
        this.whereToBuy = whereToBuy;
        this.buyTime = buyTime;
        this.sellStatus = sellStatus;
        this.category = category;
    }

    /*public Long totalPrice() {
        return stockQuantity * buyPrice;   // 총 구매 가격
    }*/

}
