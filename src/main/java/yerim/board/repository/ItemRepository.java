package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yerim.board.domain.ItemSelling;
import yerim.board.domain.ItemSold;
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

    public Item findById(Long id) {
        return em.find(Item.class, id);
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

    public List<Item> findAllKeepSelling() {
        return em.createQuery("select i from Item as i where i.status = yerim.board.domain.Status.KEEP_SELLING order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public List<Item> findAllNormalSelling() {
        return em.createQuery("select i from Item as i where i.status = yerim.board.domain.Status.NORMAL_SELLING order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public List<Item> findAllHaving() {
        return em.createQuery("select i from Item as i where i.status = yerim.board.domain.Status.HAVING order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public List<Item> findAllSold() {
        return em.createQuery("select i from Item as i where i.status = yerim.board.domain.Status.SOLD order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public ItemSold findItemSoldByItemId(Long itemId) {
        return em.createQuery("select i from ItemSold as i where i.item.id = :itemId", ItemSold.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }

    public ItemSelling findItemSellingByItemId(Long itemId) {
        return em.createQuery("select i from ItemSelling as i where i.item.id = :itemId", ItemSelling.class)
                .setParameter("itemId", itemId)
                .getSingleResult();
    }
}
