package yerim.board.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    public User() {
    }

    private String loginID;
    private String loginPW;
    private String name;
    private String email;

    private Long totalSellPrice;
    private Long totalBuyPrice;

}
