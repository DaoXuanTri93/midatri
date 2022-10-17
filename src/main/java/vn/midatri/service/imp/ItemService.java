package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.item.ItemCreate;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.mapper.ItemMapper;
import vn.midatri.repository.ItemRepository;
import vn.midatri.repository.model.Item;
import vn.midatri.service.IItemService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public List<Item> findAllByDeleted(Boolean deleted) {
        return itemRepository.findAllByDeleted(deleted);
    }

    @Override
    public List<ItemResult> findAllByCategoryId(Long id) {
        return itemRepository.findAllByCategoryId(id)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<Item> findByIdCategory(Long id) {
//        return itemRepository.findByIdCategory(id);
//    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public ItemResult save(ItemCreate itemCreate) {
        Item item = itemMapper.toModel(itemCreate);
        itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    @Override
    public ItemResult save(Item item) {
        return itemMapper.toDTO(itemRepository.save(item));
    }

//    @Override
//    public Item save(Item item) {
//        return itemRepository.save(item);
//    }

//    @Override
//    public Item save(ItemResult itemResult) {
//        return itemRepository.save(itemResult);
//    }
}
