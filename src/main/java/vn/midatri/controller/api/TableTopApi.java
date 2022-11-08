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

import static vn.midatri.repository.model.TabletopStatus.ACTIVE;

@RestController
@RequestMapping("/api/table-top")
public class TableTopApi {
    @Autowired
    private ITableTopService tableTopService;

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<TableTopResult> tableTop = tableTopService.findAll();
        return new ResponseEntity<>(tableTop, HttpStatus.OK);
    }
    @GetMapping(("/unavaliable"))
    public ResponseEntity<?> findAllNotUnavaliable() {
        List<TableTopResult> tableTop = tableTopService.findAllByStatusNot(TabletopStatus.UNAVAILABLE);
        return new ResponseEntity<>(tableTop, HttpStatus.OK);
    }
    @GetMapping("/renderTableUse")
    public ResponseEntity<?> listTableUse() {
        List<TableTopResult> tableTopResults = tableTopService.findAllByStatus(ACTIVE);
        return new ResponseEntity<>(tableTopResults, HttpStatus.OK);
    }
    @GetMapping("/renderWaitTable")
    public ResponseEntity<?> listTableDeleted() {
        List<TableTopResult> tableTopResults = tableTopService.findAllByStatus(TabletopStatus.AVAILABLE);
        return new ResponseEntity<>(tableTopResults, HttpStatus.OK);
    }
    @GetMapping("/renderNotUseTable")
    public ResponseEntity<?> listTable() {
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
        TableTop tableTop = tableTopService.findById(id);
        tableTop.setStatus(TabletopStatus.AVAILABLE);
        tableTopService.update(tableTop);
//        tableTop.setStatus(TabletopStatus.AVAILABLE);
        return new ResponseEntity<>(tableTop,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        TableTop tableTop = tableTopService.findById(id);
        return new ResponseEntity<>(tableTop, HttpStatus.OK);
    }

    @PutMapping("edit/")
    public ResponseEntity<?> editTable( @RequestBody TableTop tableTop) {
        try {
            return new ResponseEntity<>(tableTopService.update(tableTop), HttpStatus.ACCEPTED);
        }catch (Exception e){
            throw new RuntimeException("false");
        }
    }

//    @GetMapping("/page")
//    public ResponseEntity<?> findAllPt(int page) {
//       Pageable pageable= PageRequest.of(page, 24);
//        Page<TableTop> tableTops = tableTopService.findAllpt(pageable);
//        return new ResponseEntity<>(tableTops, HttpStatus.OK);
//    }
}
