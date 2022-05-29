package yerim.board.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ItemAddDTO {

    @NotBlank
    private String name;

    @NotBlank
    private int quantity;

    @NotBlank
    private String buyDate;

    @NotBlank
    private String whereToBuy;

    @NotBlank
    private Long buyPrice;

    @NotBlank
    private String category;

    @NotBlank
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
