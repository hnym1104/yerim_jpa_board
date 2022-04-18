package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yerim.board.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String getHome() {
        return "login";
    }

    @PostMapping("/")
    public String postHome(@RequestParam String loginID, @RequestParam String loginPW) {
        //로그인 확인
        log.info(loginID);
        log.info(loginPW);
        return "table";
    }

    @GetMapping("/board")
    public String getBoard() {
        return "table";
    }

    @PostMapping("/board")
    public String postBoard() {
        return "table";
    }
}
