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
        return new Item()
                .setTitle(itemCreate.getTitle())
                .setPrice(itemCreate.getPrice())
                .setQuantity(itemCreate.getQuantity())
                .setImg(itemCreate.getImg())
                .setContent(itemCreate.getContent())
                .setCategory(itemCreate.getCategory_id())
                .setUser(itemCreate.getUser_id())
                .setDeleted(itemCreate.isDeleted());
    }

    public ItemResult toDTO(Item item) {
        return new ItemResult()
                .setId(item.getId())
                .setTitle(item.getTitle())
                .setPrice(item.getPrice())
                .setQuantity(item.getQuantity())
                .setImg(item.getImg())
                .setContent(item.getContent())
                .setCategory_id(item.getCategory().getId())
                .setUser_id(item.getUser().getId())
                .setDeleted(item.isDeleted());
    }

}
