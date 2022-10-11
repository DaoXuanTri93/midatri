package vn.midatri.controller.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/table-top")
public class tableApi {
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
