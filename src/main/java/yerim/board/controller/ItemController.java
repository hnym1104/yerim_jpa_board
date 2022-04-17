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
        List<Shoes> allShoes = itemRepository.findAllShoes();
        return "item";
    }

    @GetMapping("/top")
    public String getTop() {
        List<Top> allTop = itemRepository.findAllTop();
        return "item";
    }

    @GetMapping("/bottom")
    public String getBottom() {
        List<Bottom> allBottom = itemRepository.findAllBottom();
        return "item";
    }

    @GetMapping("/stock")
    public String getStock() {
        List<Stock> allStock = itemRepository.findAllStock();
        return "item";
    }
}
