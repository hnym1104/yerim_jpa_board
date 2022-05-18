package yerim.board.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {

    @NotBlank(message = "아이디를 입력하세요.")
    private String loginID;

    @NotBlank(message = "비밀번호를 입력하세요.")
    private String loginPW;
}
