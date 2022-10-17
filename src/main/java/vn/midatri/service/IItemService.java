package vn.midatri.service;


import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;

import java.util.List;

public interface IItemService {
    List<ItemResult> findAllByDeleted(Boolean deleted);
//    List<Item> findByIdCategory(Long id);
    List<ItemResult> findAllByCategoryId(Long id);
    ItemResult findById(Long id);

    ItemResult create(CreateItem createItem);
//    Item save(Item item);
    ItemResult update(ItemResult itemResult);
}
