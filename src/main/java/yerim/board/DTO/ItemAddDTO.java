package yerim.board.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ItemAddDTO {

    private String name;
    private int quantity;
    private String buyDate;
    private String whereToBuy;
    private Long buyPrice;
    private String category;
    private String status;

    @Override
    public String toString() {
        return "ItemAddDTO{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", buyDate='" + buyDate + '\'' +
                ", whereToBuy='" + whereToBuy + '\'' +
                ", buyPrice=" + buyPrice +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
