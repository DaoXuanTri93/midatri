package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemParam;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Item;

@Component
public class ItemMapper {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper userMapper;

    public Item toModel(CreateItem createItem) {
       return new Item(createItem.getUserId(),createItem.getCategoryId())
                .setTitle(createItem.getTitle())
                .setPrice(createItem.getPrice())
                .setQuantity(createItem.getQuantity())
                .setImg(createItem.getImg())
                .setContent(createItem.getContent())
               .setUserId(createItem.getUserId())
                .setDeleted(createItem.isDeleted());

    }

    public Item toModel(ItemResult itemResult) {
        return new Item(itemResult.getUserId(),itemResult.getCategoryId() )
                .setId(itemResult.getId())
                .setTitle(itemResult.getTitle())
                .setPrice(itemResult.getPrice())
                .setQuantity(itemResult.getQuantity())
                .setImg(itemResult.getImg())
                .setContent(itemResult.getContent())

                .setDeleted(itemResult.isDeleted());
    }

    public Item toModel(ItemParam itemParam){
        return new Item(itemParam.getCategoryId(),itemParam.getUserId())
                .setId(itemParam.getId())
                .setTitle(itemParam.getTitle())
                .setPrice(itemParam.getPrice())
                .setQuantity(itemParam.getQuantity())
                .setImg(itemParam.getImg())
                .setContent(itemParam.getContent())
                .setDeleted(itemParam.isDeleted());
    }


    public ItemResult toDTO(Item item) {
        return new ItemResult()
                .setId(item.getId())
                .setTitle(item.getTitle())
                .setPrice(item.getPrice())
                .setQuantity(item.getQuantity())
                .setImg(item.getImg())
                .setContent(item.getContent())
                .setCategoryId(item.getCategoryId())
                .setUserId(item.getUserId())
                .setDeleted(item.isDeleted());
    }


//    public CreateItem toCreateItemDTO(Item item) {
//        return new CreateItem()
//                .setTitle(item.getTitle())
//                .setPrice(item.getPrice())
//                .setQuantity(item.getQuantity())
//                .setImg(item.getImg())
//                .setContent(item.getContent())
//                .setCategory(categoryMapper.toDTO(item.getCategory()))
//                .setUser(userMapper.toDTO(item.getUser()))
//                .setDeleted(item.isDeleted());
//    }
}
