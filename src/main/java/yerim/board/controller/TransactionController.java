package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yerim.board.DTO.TransactionDTO;
import yerim.board.domain.User;
import yerim.board.service.ItemService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final ItemService itemService;

    @GetMapping("monthly")
    public String getMonthly(Model model, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("loginUser");
        int month = LocalDate.now().getMonthValue();

        model.addAttribute("user", user);
        model.addAttribute("month", month);

        TransactionDTO ta = itemService.getThisMonthTA(LocalDate.now());
        log.info("ta={}", ta.toString());
        model.addAttribute("ta", ta);

        return "transaction/monthly";
    }

    @GetMapping("all_monthly")
    public String getAllMonthly(Model model, HttpSession httpSession) {
        User user = (User)httpSession.getAttribute("loginUser");
        int month = LocalDate.now().getMonthValue();   // 제일 최신 날짜

        model.addAttribute("user", user);
        model.addAttribute("month", month);

        List<TransactionDTO> TAList = itemService.getAllMonthTA();
        for (TransactionDTO TA : TAList) {
            log.info("TA={}", TA.getDate().toString());
        }
        model.addAttribute("TAList", TAList);   // 모든 달의 거래 내역

        return "transaction/all_monthly";
    }
}
