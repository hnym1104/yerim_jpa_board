package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yerim.board.domain.SellStatus;
import yerim.board.domain.item.Category;
import yerim.board.domain.item.*;
import yerim.board.repository.CategoryRepository;
import yerim.board.repository.ItemRepository;
import yerim.board.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/")
    public String getHome() {
        return "login";
    }

    @PostMapping("/login")
    public String postHome(@RequestParam String loginID, @RequestParam String loginPW) {
        log.info("loginID={}", loginID);
        log.info("loginPW={}", loginPW);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String getBoard(Model model) {
        Category bottomCate = categoryRepository.findByName("bottom");
        Bottom bottom = new Bottom();
        bottom.setName("bottom1");
        bottom.setSellStatus(SellStatus.HAVING);
        bottom.setCategory(bottomCate);
        bottom.setBuyTime(LocalDateTime.now());
        itemRepository.save(bottom);
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "board";
    }

    @PostMapping("/board")
    public String postBoard() {
        return "board";
    }

    @PostConstruct
    public void categoryInit() {   // 카테고리 우선 저장
        Category bottomCategory = new Category("bottom");
        Category topCategory = new Category("top");
        Category shoesCategory = new Category("shoes");
        Category stockCategory = new Category("stock");
        itemRepository.saveCategory(bottomCategory);
        itemRepository.saveCategory(topCategory);
        itemRepository.saveCategory(shoesCategory);
        itemRepository.saveCategory(stockCategory);
    }
}
