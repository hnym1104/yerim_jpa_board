package yerim.board.controller;

import antlr.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yerim.board.DTO.LoginDTO;
import yerim.board.domain.*;
import yerim.board.domain.item.Category;
import yerim.board.domain.item.*;
import yerim.board.repository.CategoryRepository;
import yerim.board.repository.ItemRepository;
import yerim.board.repository.UserRepository;
import yerim.board.service.ItemService;
import yerim.board.service.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    
    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping(value = {"/", "/login"})
    public String getLogin(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("loginDTO", loginDTO);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute LoginDTO loginDTO, BindingResult bindingResult, HttpSession httpSession) {
        log.info("loginID={}", loginDTO.getLoginID());
        log.info("loginPW={}", loginDTO.getLoginPW());

        if(loginDTO.getLoginID() == "") {
            bindingResult.addError(new FieldError("loginDTO", "loginID", loginDTO.getLoginID(), false, null, null, "아이디를 입력하세요"));
        }
        if(loginDTO.getLoginPW() == "") {
            bindingResult.addError(new FieldError("loginDTO", "loginPW", loginDTO.getLoginPW(), false, null, null, "비밀번호를 입력하세요"));
        }
        if(!loginDTO.getLoginID().equals("") && !loginDTO.getLoginPW().equals("") && userService.findUser(loginDTO).isEmpty()) {
            bindingResult.addError(new ObjectError("loginDTO", "아이디 또는 비밀번호가 일치하지 않습니다."));
        }
        
        if(bindingResult.hasErrors()) {
            return "login";
        }

        User user = userService.findUser(loginDTO).get();
        httpSession.setAttribute("user", user);

        return "redirect:/board";
    }

    @GetMapping("/board")
    public String getBoard(Model model, HttpSession httpSession) {
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

        return "board";
    }
}
