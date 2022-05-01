package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yerim.board.domain.item.Category;
import yerim.board.domain.item.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    @Transactional
    public void save(Item item) {
        em.persist(item);
    }

    @Transactional
    public void saveCategory(Category category) {
        em.persist(category);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item as i", Item.class).getResultList();
    }

    public List<Shoes> findAllShoes() {
        return em.createQuery("select s from Shoes as s", Shoes.class).getResultList();
    }

    public List<Top> findAllTop() {
        return em.createQuery("select t from Top as t", Top.class).getResultList();
    }

    public List<Bottom> findAllBottom() {
        return em.createQuery("select b from Bottom as b", Bottom.class).getResultList();
    }

    public List<Stock> findAllStock() {
        return em.createQuery("select s from Stock as s", Stock.class).getResultList();
    }
}
