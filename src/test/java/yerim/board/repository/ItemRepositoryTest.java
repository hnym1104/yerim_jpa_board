package yerim.board.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import yerim.board.domain.item.Item;

import javax.persistence.EntityManager;
import java.util.List;

class ItemRepositoryTest {

    private final EntityManager em;

    ItemRepositoryTest(EntityManager em) {
        this.em = em;
    }

    @Test
    public void findAllKeepSelling() {
        List<Item> result = em.createQuery("select i from Item as i where i.status = yerim.board.domain.Status.KEEP_SELLING order by i.buyDate desc", Item.class)
                .getResultList();
        if(result.isEmpty()) {
            Assertions.assertThat(result).isEmpty();
        }
    }

}