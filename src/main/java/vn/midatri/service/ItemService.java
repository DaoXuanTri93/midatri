package vn.midatri.service;


import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Item;

import java.util.List;

public interface ItemService {

    List<ItemResult> findAllByDeleted(boolean deleted);

    List<ItemResult> findAllByCategory_ParentId(long parenId);

    ItemResult findById(Long id);

    ItemResult create(CreateItem createItem);

    ItemResult update(ItemResult itemResult);

    List<ItemResult> filter(List<Long> parentIds, Long childId, boolean status);

    List<ItemResult> filter(Long childId, boolean status);

    List<ItemResult> findItemsByAllCategory(List<Long> parentIds, List<Long> childId, boolean status);
    List<ItemResult> findItemsByAllCategoryNotParentIds(List<Long> childId, boolean status);
}
