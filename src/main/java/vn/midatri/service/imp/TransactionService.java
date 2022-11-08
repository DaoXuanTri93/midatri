package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.transaction.Information;
import vn.midatri.repository.TransactionRepository;
import vn.midatri.service.ITransactionService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Override
    public List<Information> finAllByOrderIdAndItemId(Long orderId) {
        return transactionRepository.finAllByOrderIdAndItemId(orderId);
    }
}
