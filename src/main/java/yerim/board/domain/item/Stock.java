package yerim.board.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter @Setter
@DiscriminatorValue("stock")
public class Stock extends Item {

    @Enumerated(EnumType.STRING)
    private StockKind stockKind;
}
