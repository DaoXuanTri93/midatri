package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;
import vn.midatri.service.ITableTopService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/table-top")
public class TableTopApi {
    @Autowired
    private ITableTopService tableTopService;

    @GetMapping()
    public ResponseEntity<?> renderTableTop(){
        List<TableTop> tableTops = tableTopService.findAllByDeleted(false);
        return new ResponseEntity<>(tableTops, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTable(@RequestBody TableTop tableTop){
        TableTop tableTopResult = tableTopService.save(tableTop);
        return new ResponseEntity<>(tableTopResult,HttpStatus.OK);
    }

}
