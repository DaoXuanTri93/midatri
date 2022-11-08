package vn.midatri.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.dto.transaction.Information;
import vn.midatri.service.ITableTopService;
import vn.midatri.service.ITransactionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionApi {
    @Autowired
    private ITransactionService transactionService;

    @GetMapping()
    public ResponseEntity<?> render(Long orderId)  {
        List<Information> information = transactionService.finAllByOrderIdAndItemId(orderId);
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
}
