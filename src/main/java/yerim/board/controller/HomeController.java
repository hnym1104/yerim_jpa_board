package yerim.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import yerim.board.domain.Board;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        Board board = new Board();
        board.setUsername("Yerim");

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
