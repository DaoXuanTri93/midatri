package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.item.ItemCreate;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.mapper.CategoryMapper;
import vn.midatri.mapper.ItemMapper;
import vn.midatri.mapper.UserMapper;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.Item;
import vn.midatri.service.ICategoryService;
import vn.midatri.service.IItemService;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemApi {

    @Autowired
    private IItemService itemService;
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<?> renderitem(){
        List<Item> itemList = itemService.findAllByDeleted(false);
        return new ResponseEntity<>(itemList, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> findByIdCategory(@PathVariable Long id){
        List<ItemResult> item = itemService.findAllByCategoryId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdItem(@PathVariable Long id){
        Item item = itemService.findById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@RequestBody Item item, @PathVariable Long id){
        item.setId(id);
        itemService.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody ItemCreate itemCreate){

        ItemResult itemResult = itemService.save(itemCreate);
        return new ResponseEntity<>(itemResult, HttpStatus.OK);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?> deletedItem(@PathVariable Long id){
        Item item = itemService.findById(id);
        item.setDeleted(true);
        itemService.save(item);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }



}
