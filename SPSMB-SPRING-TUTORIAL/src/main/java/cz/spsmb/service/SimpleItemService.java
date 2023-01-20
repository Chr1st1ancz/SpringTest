package cz.spsmb.service;

import cz.spsmb.entity.Item;
import cz.spsmb.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SimpleItemService implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public SimpleItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private List<Item> items = new LinkedList<>();

    @Override
    public List<Item> getAll() {
        List<Item> items = new LinkedList<>();
        for(Item item: this.itemRepository.findAll()){
            items.add(item);
        }
        return items;
    }

    @Override
    public void save(Item item) {
        this.itemRepository.save(item);
    }

    @Override
    public Item getItemByName(String itemName){
        return this.itemRepository.findByName(itemName);
    }
}
