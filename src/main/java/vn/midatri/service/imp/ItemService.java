package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.mapper.ItemMapper;
import vn.midatri.repository.ItemRepository;
import vn.midatri.repository.model.Item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service

public class ItemService implements vn.midatri.service.ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ItemResult> findAllByDeleted(boolean deleted) {
        return itemRepository.findAllByDeleted(deleted)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());


    }

    @Override
    public List<ItemResult> findAllByCategory_ParentId(long parenId) {
        return itemRepository.findAllByCategory_ParentId(parenId)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public ItemResult findById(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (itemOptional.isEmpty()){
            throw new NotFoundException("Not Found id "+id);
        }
        return itemMapper.toDTO(itemOptional.get());
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

    @Override
    public List<ItemResult> filter(List<Long> parentIds, Long childId, boolean status) {
//
        return
                itemRepository.findAllByCategory(parentIds, childId, status)
                        .stream()
                        .map(item -> itemMapper.toDTO(item))
                        .collect(Collectors.toList());
    }

    @Override
    public List<ItemResult> filter(Long childId, boolean status) {
        return
                itemRepository.findAllByCategoryNotParentIds(childId, status)
                        .stream()
                        .map(item -> itemMapper.toDTO(item))
                        .collect(Collectors.toList());
    }

    @Override
    public List<ItemResult> findItemsByAllCategory(List<Long> parentIds, List<Long> childId, boolean status) {
        return itemRepository.findItemsByAllCategory(parentIds, childId, status)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResult> findItemsByAllCategoryNotParentIds(List<Long> childId, boolean status) {
        return itemRepository.findItemsByAllCategoryNotParentIds(childId, status)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemResult> findAllByCategoryId(long id) {
        return itemRepository.findAllByCategoryId(id)
                .stream()
                .map(item -> itemMapper.toDTO(item))
                .collect(Collectors.toList());
    }

}
