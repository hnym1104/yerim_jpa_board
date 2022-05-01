package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.SellStatus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@DiscriminatorValue("shoes")
public class Shoes extends Item{
    private String size;

    public Shoes() {

    }
}
