package yerim.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yerim.board.DTO.TransactionDTO;
import yerim.board.domain.ItemSold;
import yerim.board.domain.item.Item;
import yerim.board.repository.ItemRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItem() {
        List<Item> itemList = itemRepository.findAllKeepSelling();
        itemList.addAll(itemRepository.findAllNormalSelling());
        itemList.addAll(itemRepository.findAllHaving());
        itemList.addAll(itemRepository.findAllSold());
        return itemList;
    }

    public TransactionDTO getThisMonthTA(LocalDate currentDate) {
        long totalBuyPrice = 0L;
        long totalSoldPrice = 0L;
        long extra = 0L;
        long totalBene = 0L;
        long avgBene = 0L;
        int totalVolume = 0;
        LocalDate firstDay = currentDate.withDayOfMonth(1);   // 현재 달의 첫째날
        LocalDate lastDay = currentDate.withDayOfMonth(currentDate.lengthOfMonth());   // 현재 달의 마지막날
        log.info("first day={}", firstDay);
        log.info("last day={}", lastDay);
        List<Item> buyItems = itemRepository.getThisMonthBuy(firstDay, lastDay);   // 이번달 구매 내역
        for (Item buyItem : buyItems) {
            totalBuyPrice += buyItem.getTotalPrice();   // 총 구매 금액
            totalVolume += buyItem.getStockQuantity();   // 총 구매량(거래량)
        }
        List<ItemSold> soldItems = itemRepository.getThisMonthSell(firstDay, lastDay);   // 이번달 판매 내역
        for (ItemSold soldItem : soldItems) {
            totalSoldPrice += soldItem.getTotalSoldPrice();   // 총 판매 금액
            totalVolume += soldItem.getSoldQuantity();   // 총 판매량(거래량)
            extra += soldItem.getExtra();   // 부가 비용
        }
        totalBene = totalSoldPrice - totalBuyPrice - extra;   // 총 수익
        try {
            avgBene = totalBene / totalVolume;   // 거래량 별 평균 수익
        } catch(ArithmeticException e) {
            avgBene = 0;
        }
        return new TransactionDTO(currentDate, totalVolume, totalBuyPrice, totalSoldPrice, extra, totalBene, avgBene);
    }

    public List<TransactionDTO> getAllMonthTA() {
        LocalDate latestDate = LocalDate.now();   // 제일 최신 날짜
        LocalDate oldestDate = itemRepository.getOldestDate();   // 제일 이전 날짜
        log.info("oldestDate={}", oldestDate.toString());
        List<TransactionDTO> TAList = new ArrayList<>();   // 각 달의 거래 내역을 저장할 list

        LocalDate curDate = oldestDate;

        /**
         * 오늘 : 2022/05/21
         * latest : 2021/3/24
         * 2021/03/21
         */

        for(curDate = latestDate; curDate.isAfter(oldestDate); curDate = curDate.minusMonths(1)) {
            log.info("curDate={}", curDate.toString());
            // 가장 옛날 달부터 한달씩 현재까지의 거래 내역
            TAList.add(getThisMonthTA(curDate));   // 각 달의 거래 내역 가져오기
        }
        if(latestDate.getDayOfMonth() < oldestDate.getDayOfMonth()) {   // 가장 이전달의 내역을 가져오지 못했을 떄
            TAList.add(getThisMonthTA(oldestDate));   // 가장 최신달의 거래 내역 가져오기
        }

        return TAList;
    }
}
