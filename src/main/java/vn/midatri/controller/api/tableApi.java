package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.midatri.repository.model.TableTop;
import vn.midatri.service.ITableTopService;

import java.util.List;

@RequestMapping("/api/table-top")
public class tableApi {
    @Autowired
    private ITableTopService tableTopService;
    @GetMapping
    public ResponseEntity<?> findAll(){
        List<TableTop> tableTops = tableTopService.findAllByDeleted(false);
        return new ResponseEntity<>(tableTops, HttpStatus.OK);
    }
}
