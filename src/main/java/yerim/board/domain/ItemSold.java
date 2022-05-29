package yerim.board.domain;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ItemSold {   // 판매 완료된 ITEM

    @Id @GeneratedValue
    @Column(name = "item_sell_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Long soldPrice;   // 판매가
    private int soldQuantity;   // 판매 수
    private String whereToSold;   // 판매처
    private LocalDate soldTime;   // 판매일시
    private long extra;   // 부가 비용

    @Enumerated(EnumType.STRING)
    private yerim.board.domain.soldMethod soldMethod;

    public ItemSold() {
    }

    public ItemSold(User user, Item item, Long soldPrice, int soldQuantity, String whereToSold, LocalDate soldTime, long extra, yerim.board.domain.soldMethod soldMethod) {
        this.user = user;
        this.item = item;
        this.soldPrice = soldPrice;
        this.soldQuantity = soldQuantity;
        this.whereToSold = whereToSold;
        this.soldTime = soldTime;
        this.extra = extra;
        this.soldMethod = soldMethod;
    }

    public long getTotalSoldPrice() {
        return soldQuantity * soldPrice;
    }
}
