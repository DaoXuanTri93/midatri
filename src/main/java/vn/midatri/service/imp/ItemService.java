package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.item.CreateItem;
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
    public List<ItemResult> findAllByDeleted(Boolean deleted) {
        return itemRepository.findAllByDeleted(deleted)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());

    }

    @Override
    public List<ItemResult> findAllByCategoryId(Long id) {
        return itemRepository.findAllByCategoryId(id)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }


    @Override
    public ItemResult findById(Long id) {
        return itemMapper.toDTO(itemRepository.findById(id).get());
    }

    @Override
    public ItemResult create(CreateItem createItem) {
        Item item = itemMapper.toModel(createItem);
        itemRepository.save(item);
        return itemMapper.toDTO(item);
    }

    @Override
    public ItemResult update(ItemResult itemResult) {

        itemRepository.save(itemMapper.toModel(itemResult));
        return itemResult;
    }

}
