package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import yerim.board.domain.User;
import yerim.board.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/join")
    public String getJoin(Model model) {
        log.info("UserController.getJoin");
        model.addAttribute("user", new User());
        return "user/join";
    }

    @PostMapping("/join")
    public String postJoin(@ModelAttribute User user) {
        userRepository.save(user);
        return "login";
    }
}
