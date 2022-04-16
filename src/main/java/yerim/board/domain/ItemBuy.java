package yerim.board.domain;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.item.Item;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class ItemBuy {

    @Id
    @GeneratedValue
    @Column(name = "item_sell_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Long buyPrice;
    private String whereToBuy;   // 구매처
    private LocalDateTime buyTime;   // 구매일시
}
