package yerim.board.domain.item;

import lombok.Getter;

@Getter
public enum StockKind {
    DIGITAL("전자기기"), CARD("카드"), GAME("게임"), STICKER("스티커");

    private String description;

    StockKind(String description) {
        this.description = description;
    }
}
