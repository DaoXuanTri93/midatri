package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemParam;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.Item;
import vn.midatri.repository.model.User;

@Component
public class ItemMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper userMapper;

    public Item toModel(CreateItem createItem) {
        return new Item()
                .setTitle(createItem.getTitle())
                .setPrice(createItem.getPrice())
                .setQuantity(createItem.getQuantity())
                .setImg(createItem.getImg())
                .setContent(createItem.getContent())
                .setCategoryId(createItem.getCategory())
                .setUserId(createItem.getUser())
                .setDeleted(createItem.isDeleted());
    }

//    public Item toModel(ItemResult itemResult) {
//        return new Item()
//                .setId(itemResult.getId())
//                .setTitle(itemResult.getTitle())
//                .setPrice(itemResult.getPrice())
//                .setQuantity(itemResult.getQuantity())
//                .setImg(itemResult.getImg())
//                .setContent(itemResult.getContent())
//                .setCategory(categoryMapper.toModel(itemResult.getCategory()))
//                .setUser(userMapper.toModel(itemResult.getUser()))
//                .setDeleted(itemResult.isDeleted());
//    }


    public ItemResult toDTO(Item item) {
        return new ItemResult()
                .setId(item.getId())
                .setTitle(item.getTitle())
                .setPrice(item.getPrice())
                .setQuantity(item.getQuantity())
                .setImg(item.getImg())
                .setContent(item.getContent())
              //  .setCategory(categoryMapper.toDTO(item.getCategory()))
              //  .setUser(userMapper.toDTO(item.getUser()))
                .setDeleted(item.isDeleted());
    }


    public CreateItem toCreateItemDTO(Item item) {
        return new CreateItem()
                .setTitle(item.getTitle())
                .setPrice(item.getPrice())
                .setQuantity(item.getQuantity())
                .setImg(item.getImg())
                .setContent(item.getContent())
                .setCategory(item.getCategoryId())
                .setUser(item.getUserId())
                .setDeleted(item.isDeleted());
    }
}
