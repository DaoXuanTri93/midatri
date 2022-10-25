package vn.midatri.service;


import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;

import java.util.List;

public interface IItemService {

    List<ItemResult> findAllByDeleted(Boolean deleted);

    ItemResult findById(Long id);

    ItemResult create(CreateItem createItem);

    ItemResult update(ItemResult itemResult);
}
