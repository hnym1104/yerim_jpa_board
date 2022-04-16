package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yerim.board.domain.item.Item;

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

    public List<Item> findByType(String item_type) {
       return em.createQuery("select i from Item i where i.category = :item_type", Item.class)
                .setParameter("item_type", item_type)
                .getResultList();
    }
}
