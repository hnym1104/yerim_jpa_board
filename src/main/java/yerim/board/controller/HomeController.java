package yerim.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import yerim.board.DTO.LoginDTO;
import yerim.board.domain.*;
import yerim.board.service.ItemService;
import yerim.board.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    
    private final ItemService itemService;
    private final UserService userService;

    @GetMapping(value = {"/", "/login"})
    public String getLogin(Model model) {
        LoginDTO loginDTO = new LoginDTO();
        model.addAttribute("loginDTO", loginDTO);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@Validated @ModelAttribute LoginDTO loginDTO, BindingResult bindingResult, HttpSession httpSession) {
        log.info("loginID={}", loginDTO.getLoginID());
        log.info("loginPW={}", loginDTO.getLoginPW());

        if(bindingResult.hasErrors()) {   // 필드 검증 오류
            return "login";
        }
        User loginUser = userService.login(loginDTO);

        if(loginUser == null) {   // 글로벌 검증 오류
            bindingResult.addError(new ObjectError("loginDTO", "아이디 또는 비밀번호가 일치하지 않습니다."));
            return "login";
        }

        httpSession.setAttribute("loginUser", loginUser);   // 로그인 성공

        return "redirect:transaction/monthly";
    }


}
