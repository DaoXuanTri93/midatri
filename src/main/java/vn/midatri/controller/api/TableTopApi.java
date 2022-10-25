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
import vn.midatri.repository.model.TabletopStatus;
import vn.midatri.service.ITableTopService;

import java.util.List;
import java.util.Optional;

import static vn.midatri.repository.model.TabletopStatus.AVAILABLE;

@RestController
@RequestMapping("/api/table-top")
public class TableTopApi {
    @Autowired
    private ITableTopService tableTopService;

//    @GetMapping()
//    public ResponseEntity<?> renderTableTop(){
//        List<TableTopResult> tableTop = tableTopService.findAllByStatus(TabletopStatus.AVAILABLE);
//        return new ResponseEntity<>(tableTop, HttpStatus.OK);
//    }

//    @GetMapping("/renderDeletedTable")
//    public ResponseEntity<?> listTableDeleted(){
//        List<TableTopResult> tableTopResults = tableTopService.findAllByStatus(AVAILABLE);
//        return new ResponseEntity<>(tableTopResults,HttpStatus.OK);
//    }
    @GetMapping
    public ResponseEntity<?> findAllAvailableTableTop() {
        List<TableTopResult> tableTop = tableTopService.findAllByStatus(TabletopStatus.AVAILABLE);
        return new ResponseEntity<>(tableTop, HttpStatus.OK);
    }

    @GetMapping("/renderDeletedTable")
    public ResponseEntity<?> listTableDeleted() {
        List<TableTopResult> tableTopResults = tableTopService.findAllByStatus(TabletopStatus.UNAVAILABLE);
        return new ResponseEntity<>(tableTopResults, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewTable(@RequestBody TableTopRegister tableTopRegister) {
        TableTopResult tableTopResult = tableTopService.create(tableTopRegister);
        return new ResponseEntity<>(tableTopResult, HttpStatus.OK);
    }

    @PostMapping("/deleted/{id}")
    public ResponseEntity<?> deletedTable(@PathVariable Long id) {
//        tableTop.setStatus(true);
//        tableTopService.update(tableTop);
//
        TableTop tableTop = tableTopService.findById(id);
        tableTop.setStatus(TabletopStatus.UNAVAILABLE);
//        tableTopService.save(tableTop);
//        TableTop tableTop = tableTopService.findById(id);
//        tableTop.setStatus(TabletopStatus.UNAVAILABLE);
        // tableTopService.save(tableTop);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/restore/{id}")
    public ResponseEntity<?> restoreTable(@PathVariable Long id) {
//        tableTop.setStatus(false);
//        tableTopService.update(tableTop);
//        tableTop.setStatus(TabletopStatus.AVAILABLE);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        TableTop tableTop = tableTopService.findById(id);
        return new ResponseEntity<>(tableTop, HttpStatus.OK);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<?> editTable(@PathVariable Long id, @RequestBody TableTop tableTop) {
        TableTopResult tableTopResult = tableTopService.findTableById(id);
        tableTop.setId(tableTop.getId());
        tableTopService.update(tableTop);
        return new ResponseEntity<>(tableTop, HttpStatus.OK);

    }

}
