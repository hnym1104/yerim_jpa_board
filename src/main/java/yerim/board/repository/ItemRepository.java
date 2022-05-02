package yerim.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import yerim.board.domain.SellStatus;
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
        return em.createQuery("select i from Item as i where i.sellStatus = yerim.board.domain.SellStatus.KEEP_SELLING order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public List<Item> findAllNormalSelling() {
        return em.createQuery("select i from Item as i where i.sellStatus = yerim.board.domain.SellStatus.NORMAL_SELLING order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public List<Item> findAllHaving() {
        return em.createQuery("select i from Item as i where i.sellStatus = yerim.board.domain.SellStatus.HAVING order by i.buyTime desc", Item.class)
                .getResultList();
    }

    public List<Item> findAllSold() {
        return em.createQuery("select i from Item as i where i.sellStatus = yerim.board.domain.SellStatus.SOLD order by i.buyTime desc", Item.class)
                .getResultList();
    }
}
