package yerim.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yerim.board.domain.*;
import yerim.board.domain.item.*;
import yerim.board.repository.CategoryRepository;
import yerim.board.repository.ItemRepository;
import yerim.board.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class InitMethod {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    public void categoryInit() {   // 필요 data 우선 저장
        User user = new User("admin", "admin!", "sudoUser", "user@naver.com");
        userRepository.save(user);
        User findUser = userRepository.findOne(user.getId());

        Category bottomCategory = new Category("bottom");
        Category topCategory = new Category("top");
        Category shoesCategory = new Category("shoes");
        Category stockCategory = new Category("stock");
        categoryRepository.save(bottomCategory);
        categoryRepository.save(topCategory);
        categoryRepository.save(shoesCategory);
        categoryRepository.save(stockCategory);

        Bottom bottom1 = new Bottom("bottom1", 1, 20000L, "KREAM", LocalDate.of(2021, 5, 2)
                , Status.KEEP_SELLING, bottomCategory, "M");
        Top top1 = new Top("top1", 1, 40000L, "KREAM", LocalDate.of(2021, 5, 4)
                , Status.KEEP_SELLING, topCategory, "L");

        Shoes shoes1 = new Shoes("shoes1", 1, 100000L, "KREAM", LocalDate.of(2021, 7, 2)
                , Status.NORMAL_SELLING, shoesCategory, "260");
        Stock stock1 = new Stock("stock1", 4, 5000L, "COUPANG", LocalDate.of(2021, 6, 5)
                , Status.NORMAL_SELLING, stockCategory, StockKind.CARD);

        Bottom bottom2 = new Bottom("bottom2", 1, 20000L, "KREAM", LocalDate.of(2021, 8, 2)
                , Status.HAVING, bottomCategory, "M");
        Top top2 = new Top("top2", 1, 40000L, "KREAM", LocalDate.of(2021, 5, 4)
                , Status.HAVING, topCategory, "L");

        Shoes shoes2 = new Shoes("shoes2", 1, 100000L, "KREAM", LocalDate.of(2021, 7, 2)
                , Status.SOLD, shoesCategory, "250");
        Stock stock2 = new Stock("stock2", 4, 5000L, "COUPANG", LocalDate.of(2021, 6, 5)
                , Status.SOLD, stockCategory, StockKind.STICKER);
        itemRepository.save(bottom1);
        itemRepository.save(bottom2);
        itemRepository.save(top1);
        itemRepository.save(top2);
        itemRepository.save(shoes1);
        itemRepository.save(shoes2);
        itemRepository.save(stock1);
        itemRepository.save(stock2);

        ItemSelling itemSelling_bottom1 = new ItemSelling(findUser, bottom1, 30000L, "KREAM", Status.KEEP_SELLING);
        ItemSelling itemSelling_top1 = new ItemSelling(findUser, top1, 50000L, "KREAM", Status.KEEP_SELLING);
        ItemSelling itemSelling_shoes1 = new ItemSelling(findUser, shoes1, 100000L, "KREAM", Status.NORMAL_SELLING);
        ItemSelling itemSelling_stock1 = new ItemSelling(findUser, stock1, 100000L, "COUPANG", Status.NORMAL_SELLING);
        itemRepository.saveSellingItem(itemSelling_bottom1);
        itemRepository.saveSellingItem(itemSelling_top1);
        itemRepository.saveSellingItem(itemSelling_shoes1);
        itemRepository.saveSellingItem(itemSelling_stock1);

        ItemSold itemSold_shoes2 = new ItemSold(findUser, shoes2, 200000L, 1, "중고나라", LocalDate.of(2022, 5, 4), 1000L, soldMethod.KEEP_SOLD);
        ItemSold itemSold_stock2 = new ItemSold(findUser, stock2, 200000L, 1, "당근마켓", LocalDate.of(2022, 5, 1), 1000L, soldMethod.NORMAL_SOLD);
        itemRepository.saveSoldItem(itemSold_shoes2);
        itemRepository.saveSoldItem(itemSold_stock2);
    }
}
