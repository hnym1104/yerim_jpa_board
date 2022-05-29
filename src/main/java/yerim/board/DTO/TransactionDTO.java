package yerim.board.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {

    private LocalDate date;
    private int tradingVolume;
    private long totalBuyPrice;
    private long totalSellPrice;
    private long extra;
    private long totalBene;
    private long aveBene;

    public TransactionDTO(LocalDate date, int tradingVolume, long totalBuyPrice, long totalSellPrice, long extra, long totalBene, long aveBene) {
        this.date = date;
        this.tradingVolume = tradingVolume;
        this.totalBuyPrice = totalBuyPrice;
        this.totalSellPrice = totalSellPrice;
        this.extra = extra;
        this.totalBene = totalBene;
        this.aveBene = aveBene;
    }
}
