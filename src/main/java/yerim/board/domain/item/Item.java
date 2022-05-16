package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.Status;
import yerim.board.domain.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_type")
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;   // 해당 아이템 소유자 - 로그인 구현 시 사용(사용자가 여러명일 경우)

    private String name;
    private int stockQuantity;
    private Long buyPrice;   // 상품 개당 가격
    private String whereToBuy;   // 구매처

    private LocalDate buyDate;   // 구매 날짜
    private LocalDate uploadTime;   // 상품 정보 업로드 시간

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Item() {
    }

    public Item(String name, int stockQuantity, Long buyPrice, String whereToBuy, LocalDate buyDate, Status status, Category category) {
        this.name = name;
        this.stockQuantity = stockQuantity;
        this.buyPrice = buyPrice;
        this.whereToBuy = whereToBuy;
        this.buyDate = buyDate;
        this.status = status;
        this.category = category;
    }

    /*public Long totalPrice() {
        return stockQuantity * buyPrice;   // 총 구매 가격
    }*/

}
