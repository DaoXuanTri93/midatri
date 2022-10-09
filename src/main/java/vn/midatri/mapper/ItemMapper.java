package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.item.ItemCreate;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Item;
import vn.midatri.repository.model.User;

@Component
public class ItemMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper userMapper;

    public Item toModel(ItemCreate itemCreate) {
        Item item = new Item();
        item.setTitle(itemCreate.getTitle());
        item.setPrice(itemCreate.getPrice());
        item.setQuantity(itemCreate.getQuantity());
        item.setImg(itemCreate.getImg());
        item.setContent(itemCreate.getContent());
        item.setCategory(itemCreate.getCategory_id());
        item.setUser(itemCreate.getUser_id());
        return item;
    }

    public ItemResult toDTO(Item item) {
        ItemResult itemResult = new ItemResult();
        itemResult.setId(item.getId());
        itemResult.setTitle(item.getTitle());
        itemResult.setPrice(item.getPrice());
        itemResult.setQuantity(item.getQuantity());
        itemResult.setImg(item.getImg());
        itemResult.setContent(item.getContent());
        itemResult.setCategoryResult(categoryMapper.toDTO(item.getCategory()));
        itemResult.setUserResult(userMapper.toDTO(item.getUser()));
        return itemResult;
    }

    public Item toItem(ItemResult itemResult) {
        Item item = new Item();
        item.setId(itemResult.getId());
        item.setTitle(itemResult.getTitle());
        item.setPrice(itemResult.getPrice());
        item.setQuantity(itemResult.getQuantity());
        item.setImg(itemResult.getImg());
        item.setContent(itemResult.getContent());
        item.setCategory(categoryMapper.toCategory(itemResult.getCategoryResult()));
        item.setUser(userMapper.toUser(itemResult.getUserResult()));
        return item;
    }
}
