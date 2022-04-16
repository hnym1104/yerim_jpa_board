package yerim.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import yerim.board.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ItemSell {

    @Id @GeneratedValue
    @Column(name = "item_sell_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Long sellPrice;
    private String whereToSell;   // 판매처
    private LocalDateTime sellTime;   // 판매일시

    @Enumerated(EnumType.STRING)
    private SellStatus sellStatus;   // 판매 상태(예약중, 판매완료)
}
