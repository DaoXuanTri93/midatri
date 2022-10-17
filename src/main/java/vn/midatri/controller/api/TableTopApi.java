package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        List<TableTop> tableTops = tableTopService.findAll();
        return new ResponseEntity<>(tableTops, HttpStatus.OK);
    }
}
