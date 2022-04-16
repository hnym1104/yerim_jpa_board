package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yerim.board.domain.User;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
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
}
