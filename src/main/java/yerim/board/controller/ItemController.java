package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import yerim.board.domain.item.*;
import yerim.board.repository.ItemRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @GetMapping("/shoes")
    public String getShoes() {
        Shoes shoes = new Shoes();
        shoes.setName("shoes1");
        itemRepository.save(shoes);
        List<Item> shoes1 = itemRepository.findByType("shoes");
        for (Item item : shoes1) {
            System.out.println("item.getName() = " + item.getName());
        }
        return "item";
    }

    @GetMapping("/top")
    public String getTop() {
        Top top = new Top();
        top.setName("top1");
        itemRepository.save(top);
        return "item";
    }

    @GetMapping("/bottom")
    public String getBottom() {
        Bottom bottom = new Bottom();
        bottom.setName("bottom1");
        itemRepository.save(bottom);
        return "item";
    }

    @GetMapping("/stock")
    public String getStock() {
        Stock stock = new Stock();
        stock.setName("stock1");
        stock.setStockKind(StockKind.CARD);
        itemRepository.save(stock);
        return "item";
    }
}
