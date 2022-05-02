package yerim.board.domain;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.item.Item;

import javax.persistence.*;
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
    private String whereToSold;   // 판매처
    private LocalDateTime soldTime;   // 판매일시

    @Enumerated(EnumType.STRING)
    private yerim.board.domain.soldMethod soldMethod;
}
