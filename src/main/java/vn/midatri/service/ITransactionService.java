package vn.midatri.service;

import vn.midatri.dto.transaction.Information;

import java.util.Date;
import java.util.List;

public interface ITransactionService {
    List<Information> finAllByOrderIdAndItemId(Long orderId);
}
