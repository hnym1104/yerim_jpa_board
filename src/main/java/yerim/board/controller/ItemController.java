package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yerim.board.DTO.ItemAddDTO;
import yerim.board.domain.Status;
import yerim.board.domain.User;
import yerim.board.domain.item.*;
import yerim.board.repository.ItemRepository;
import yerim.board.service.ItemService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/item")
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    public String getItemList(Model model, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("user");
        if(user == null) {
            model.addAttribute("hello", "회원님 안녕하세요!");
        } else {
            model.addAttribute("hello", user.getName() + "님 안녕하세요!");
        }
        List<Item> items = itemService.getAllItem();
        if(items.isEmpty()) {
            model.addAttribute("hasItems", false);
        } else {
            model.addAttribute("hasItems", true);
            model.addAttribute("items", items);
        }

        return "item/list";
    }

    @GetMapping("types/{types}")
    public String itemTypes(@PathVariable String types, Model model) {
        log.info("types={}", types);
        List<Item> items = new ArrayList<>();
        if(types.equals("keep_selling")) {
            items = itemRepository.findAllKeepSelling();
        } else if(types.equals("normal_selling")) {
            items = itemRepository.findAllNormalSelling();
        } else if(types.equals("having")) {
            items = itemRepository.findAllHaving();
        } else if(types.equals("sold")) {
            items = itemRepository.findAllSold();
        }
        if(items.isEmpty()) {   // list empty
            model.addAttribute("hasItems", false);
        } else {
            model.addAttribute("hasItems", true);
            model.addAttribute("items", items);
        }
        return "/board";
    }

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
        model.addAttribute("itemAddDto", new ItemAddDTO());
        return "item/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ItemAddDTO itemAddDTO) {
        System.out.println("itemAddDTO = " + itemAddDTO.toString());
        return "redirect:/board";
    }


}
