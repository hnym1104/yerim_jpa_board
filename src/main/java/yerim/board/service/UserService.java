package yerim.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import yerim.board.DTO.LoginDTO;
import yerim.board.domain.User;
import yerim.board.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUser(LoginDTO loginDTO) {
        return userRepository.findOneByIdPw(loginDTO.getLoginID(), loginDTO.getLoginPW());
    }
}
