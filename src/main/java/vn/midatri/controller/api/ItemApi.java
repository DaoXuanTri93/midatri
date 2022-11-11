package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.service.ItemService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/findAll")
    public ResponseEntity<?> renderAllItem(){
        List<ItemResult> items = itemService.findAll();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

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
    public ResponseEntity<?> findAllByCategory(String cp,Long cc,boolean s){
        System.out.println(cp);
        if (cp==null){
           return new ResponseEntity<>( itemService.filter(cc,s),HttpStatus.OK);
        }
        String[] cps = cp.split(",");
        List<Long> parentIds = Arrays.stream(cps).map(Long::parseLong).collect(Collectors.toList());
        List<ItemResult> itemResults = itemService.filter(parentIds,cc,s);
        return new ResponseEntity<>(itemResults,HttpStatus.OK);
    }
    @GetMapping("/allCategory")
    public ResponseEntity<?> findAllByAllCategory(String cp,String cc,boolean s){
        String[] ccs= cc.split(",");
        List<Long>childId = Arrays.stream(ccs).map(Long::parseLong).collect(Collectors.toList());
        if (cp==null){
            return new ResponseEntity<>( itemService.findItemsByAllCategoryNotParentIds(childId,s),HttpStatus.OK);
        }
        String[] cps = cp.split(",");
        List<Long> parentIds = Arrays.stream(cps).map(Long::parseLong).collect(Collectors.toList());
        List<ItemResult> itemResults = itemService.findItemsByAllCategory(parentIds,childId,s);
        return new ResponseEntity<>(itemResults,HttpStatus.OK);
    }
    @GetMapping("/filterItem/{id}")
    public ResponseEntity<?> findAllByCategoryId(@PathVariable long id){
        List<ItemResult> itemResults = itemService.findAllByCategoryId(id);
        return new ResponseEntity<>(itemResults,HttpStatus.OK);
    }

}
