package vn.midatri.service;


import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemParam;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Item;

import java.util.List;

public interface IItemService {

    List<ItemResult> findAllByDeleted(Boolean deleted);

    List<ItemResult> findAllByCategory_ParentId(long parenId);

    ItemResult findById(Long id);

    ItemResult create(CreateItem createItem);

    ItemResult update(ItemResult itemResult);
}
