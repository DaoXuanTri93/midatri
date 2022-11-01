package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemApi {

    @Autowired
    private ItemService itemService;



    @GetMapping
    public ResponseEntity<?> renderItem( boolean deleted){
        List<ItemResult> items = itemService.findAllByDeleted(deleted);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
//    @GetMapping("/restore")
//    public ResponseEntity<?> restoreItem(){
//        List<ItemResult> itemList = itemService.findAllByDeleted(true);
//        return new ResponseEntity<>(itemList, HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdItem(@PathVariable Long id){
        ItemResult itemResult = itemService.findById(id);
        return new ResponseEntity<>(itemResult, HttpStatus.OK);
    }

    @GetMapping("/find/{parentId}")
    public ResponseEntity<?> findAllByCategory_ParentId(@PathVariable long parentId){
        List<ItemResult> itemResul = itemService.findAllByCategory_ParentId(parentId);
        return new ResponseEntity<>(itemResul,HttpStatus.OK);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@RequestBody ItemResult itemResult, @PathVariable Long id){
        itemResult.setId(id);
        itemService.update(itemResult);
        return new ResponseEntity<>(itemResult, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createItem(@RequestBody CreateItem createItem){

        ItemResult itemResult = itemService.create(createItem);
        return new ResponseEntity<>(itemResult, HttpStatus.OK);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?> deletedItem(@PathVariable Long id){
        ItemResult itemResult = itemService.findById(id);
        itemResult.setDeleted(true);
        itemService.update(itemResult);
        return new ResponseEntity<>(itemResult, HttpStatus.OK);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreItem(@PathVariable Long id){
        ItemResult itemResult = itemService.findById(id);
        itemResult.setDeleted(false);
        itemService.update(itemResult);
        return new ResponseEntity<>(itemResult, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findAllByCategory( List<Long> cp,Long cc,boolean s){
        List<ItemResult> itemResults = itemService.filter(cp,cc,s);
        return new ResponseEntity<>(itemResults,HttpStatus.OK);
    }

    @GetMapping("/filterItem/{id}")
    public ResponseEntity<?> findAllByCategoryId(@PathVariable long id){
        List<ItemResult> itemResults = itemService.findAllByCategoryId(id);
        return new ResponseEntity<>(itemResults,HttpStatus.OK);
    }

}
