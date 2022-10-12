package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.repository.TableTopRepository;
import vn.midatri.repository.model.TableTop;
import vn.midatri.service.ITableTopService;

import java.util.List;

@Service
@Transactional
public class TableTopService implements ITableTopService {

    @Autowired
    private TableTopRepository tableTopRepository;
    @Override
    public List<TableTop> findAllByDeleted(Boolean status) {
        return tableTopRepository.findAllByDeleted(status);
    }
}
