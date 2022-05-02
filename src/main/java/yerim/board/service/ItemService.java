package yerim.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yerim.board.domain.item.Item;
import yerim.board.repository.ItemRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> getAllItem() {
        List<Item> itemList = itemRepository.findAllKeepSelling();
        itemList.addAll(itemRepository.findAllNormalSelling());
        itemList.addAll(itemRepository.findAllHaving());
        itemList.addAll(itemRepository.findAllSold());
        return itemList;
    }
}
