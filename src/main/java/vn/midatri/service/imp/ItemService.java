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

public class ItemService implements IItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ItemResult> findAllByDeleted(Boolean deleted) {
        return itemRepository.findAllByDeleted(deleted)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());


    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemResult> findAllByCategoryId(Long id) {
        return itemRepository.findAllByCategoryId(id)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public ItemResult findById(Long id) {
        return itemMapper.toDTO(itemRepository.findById(id).get());
    }

    @Override
    @Transactional
    public ItemResult create(CreateItem createItem) {

        return itemMapper.toDTO(itemRepository.save(itemMapper.toModel(createItem)));
    }

    @Override
    @Transactional
    public ItemResult update(ItemResult itemResult) {
        return itemMapper.toDTO(itemRepository.save(itemMapper.toModel(itemResult)));
    }

}
