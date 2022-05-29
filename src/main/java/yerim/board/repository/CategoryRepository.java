package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import yerim.board.domain.item.Category;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryRepository {

    private final EntityManager em;

    @Transactional
    public void save(Category category) {
        em.persist(category);
    }

    public Category findByName(String name) {
        return em.createQuery("select c from Category as c where c.name = :name", Category.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
