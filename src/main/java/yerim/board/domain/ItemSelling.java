package yerim.board.domain;

import lombok.Data;
import yerim.board.domain.item.Item;

import javax.persistence.*;

@Entity
@Data
public class ItemSelling {

    @Id @GeneratedValue
    @Column(name = "item_selling_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Long sellingPrice;   // 판매 중인 가격
    private Long whereToSelling;   // 판매중인 곳
    private Status status;   // 창고인지 일반인지
}
