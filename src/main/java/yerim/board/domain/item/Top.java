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
@DiscriminatorValue("top")
public class Top extends Item {
    private String size;

    public Top() {

    }
}
