package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.mapper.TableTopMapper;
import vn.midatri.repository.TableTopRepository;
import vn.midatri.repository.model.TableTop;
import vn.midatri.service.ITableTopService;

import java.util.List;

@Service
@Transactional
public class TableTopService implements ITableTopService {

    @Autowired
    private TableTopRepository tableTopRepository;

    @Autowired
    TableTopMapper tableTopMapper;


    @Override
    public List<TableTop> findAll() {
        return tableTopRepository.findAll();
    }

    @Override
    public List<TableTop> findAllByDeleted(boolean deleted) {
        return tableTopRepository.findAllByDeleted(deleted);
    }

    @Override
    public TableTop save(TableTop tableTop) {
        return tableTopRepository.save(tableTop);
    }




}
