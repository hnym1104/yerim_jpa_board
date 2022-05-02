package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import yerim.board.domain.Status;
import yerim.board.domain.item.*;
import yerim.board.repository.ItemRepository;
import yerim.board.service.ItemService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/shoes")
    public String getShoes() {
        List<Shoes> allShoes = itemRepository.findAllShoes();
        return "item/itemInfo";
    }

    @GetMapping("/top")
    public String getTop() {
        List<Top> allTop = itemRepository.findAllTop();
        return "item/itemInfo";
    }

    @GetMapping("/bottom")
    public String getBottom() {
        List<Bottom> allBottom = itemRepository.findAllBottom();
        return "item/itemInfo";
    }

    @GetMapping("/stock")
    public String getStock() {
        List<Stock> allStock = itemRepository.findAllStock();
        return "item/itemInfo";
    }

    @GetMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        log.info("item={}", item.getName());
        model.addAttribute("item", item);
        if (item.getStatus() == Status.SOLD) {
            model.addAttribute("itemSold", itemRepository.findItemSoldByItemId(item.getId()));
        } else if(item.getStatus() == Status.KEEP_SELLING || item.getStatus() == Status.NORMAL_SELLING) {
            model.addAttribute("itemSelling", itemRepository.findItemSellingByItemId(item.getId()));
        }
        return "item/itemInfo";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("item", new Item());
        return "item/add";
    }
}
