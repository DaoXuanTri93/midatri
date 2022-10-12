package vn.midatri.util.service;


import vn.midatri.dto.item.ItemCreate;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Item;

import java.util.List;

public interface IItemService {
    List<Item> findAllByDeleted(Boolean deleted);
//    List<Item> findByIdCategory(Long id);
    List<Item> findAllByCategoryId(Long id);
    Item findById(Long id);

    ItemResult save(ItemCreate itemCreate);
    Item save(Item item);
}
