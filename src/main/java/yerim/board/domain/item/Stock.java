package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;
import yerim.board.domain.Status;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
@Getter @Setter
@DiscriminatorValue("stock")
public class Stock extends Item {

    @Enumerated(EnumType.STRING)
    private StockKind stockKind;

    public Stock() {

    }

    public Stock(String name, int stockQuantity, Long buyPrice, String whereToBuy, LocalDate buyTime, Status sellStatus, Category category, StockKind stockKind) {
        super(name, stockQuantity, buyPrice, whereToBuy, buyTime, sellStatus, category);
        this.stockKind = stockKind;
    }
}
