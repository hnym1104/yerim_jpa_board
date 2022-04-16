package yerim.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "login";
    }

    @GetMapping("/board")
    public String getBoard() {
        return "table";
    }

    @PostMapping("/board")
    public String postBoard() {
        //로그인 확인 후
        return "table";
    }
}
