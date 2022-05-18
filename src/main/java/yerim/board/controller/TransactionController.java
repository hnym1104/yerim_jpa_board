package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import yerim.board.domain.User;
import yerim.board.service.ItemService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

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
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);   // 현재 달의 첫째날
        LocalDate lastDay = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());   // 현재 달의 마지막날
        log.info("first day={}", firstDay);
        log.info("last day={}", lastDay);
        model.addAttribute("user", user);
        model.addAttribute("month", month);

        return "transaction/monthly";
    }
}
