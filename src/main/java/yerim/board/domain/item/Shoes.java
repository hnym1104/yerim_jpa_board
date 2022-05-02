package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.Status;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter @Setter
@DiscriminatorValue("shoes")
public class Shoes extends Item{
    private String size;

    public Shoes() {

    }

    public Shoes(String name, int stockQuantity, Long buyPrice, String whereToBuy, LocalDate buyTime, Status sellStatus, Category category, String size) {
        super(name, stockQuantity, buyPrice, whereToBuy, buyTime, sellStatus, category);
        this.size = size;
    }
}
