package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        log.info("ItemController.item");
        Item item = itemRepository.findById(itemId);
        log.info("item={}", item.getId());
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

    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {
        return "redirect:board";
    }


}
