package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import yerim.board.domain.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long userId) {
        return em.find(User.class, userId);
    }

    public List<User> findAll() {
        return em.createQuery("select m from User as m", User.class)
                .getResultList();
    }

    public Optional<User> findOneByIdPw(String loginID, String loginPW) {   // ID, PW 동일한 회원 검색
        List<User> resultList = em.createQuery("select u from User as u where u.loginID = :loginID and u.loginPW = :loginPW", User.class)
                .setParameter("loginID", loginID)
                .setParameter("loginPW", loginPW)
                .getResultList();
        return resultList.stream().findAny();
    }
}
