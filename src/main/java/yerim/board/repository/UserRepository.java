package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import yerim.board.domain.User;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
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

    public Optional<User> findOneById(String loginID) {
        return findAll().stream()
                .filter(m -> m.getLoginID().equals(loginID))
                .findFirst();
    }

    @Transactional   // 해당 어노테이션 없으면 영속성 컨텍스트에만 반영되고 DB랑 동기화 X
    public void update(User user) {
        user.setName("NEWNEW User");
    }
}
