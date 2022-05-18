package yerim.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yerim.board.DTO.LoginDTO;
import yerim.board.domain.User;
import yerim.board.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User login(LoginDTO loginDTO) {
        return userRepository.findOneById(loginDTO.getLoginID())
                .filter(m -> m.getLoginPW().equals(loginDTO.getLoginPW()))
                .orElse(null);
    }
}
