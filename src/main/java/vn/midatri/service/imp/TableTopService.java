package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.mapper.TableTopMapper;
import vn.midatri.repository.TableTopRepository;
import vn.midatri.repository.model.TableTop;
import vn.midatri.repository.model.TabletopStatus;
import vn.midatri.service.ITableTopService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TableTopService implements ITableTopService {

    @Autowired
    private TableTopRepository tableTopRepository;

    @Autowired
    TableTopMapper tableTopMapper;


    @Override
    public List<TableTopResult> findAll() {
        return tableTopRepository.findAll()
                .stream()
                .map(tableTop -> tableTopMapper.toDTO(tableTop))
                .collect(Collectors.toList());
    }

    @Override
    public List<TableTopResult> findAllByStatus(TabletopStatus status) {
        return tableTopRepository.findAllByStatus(status)
//        return tableTopRepository.findAllByStatus(status)
                .stream()
                .map(tableTop -> tableTopMapper.toDTO(tableTop))
                .collect(Collectors.toList());
    }

    @Override
    public List<TableTopResult> findAllByStatusNot(TabletopStatus status) {
        return tableTopRepository.findAllByStatusNot(status)
                .stream()
                .map(tableTop -> tableTopMapper.toDTO(tableTop))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<TableTopResult> findAllByDeleted(TabletopStatus status) {
//        return tableTopRepository.findAllByDeleted(TabletopStatus.UNAVAILABLE)
//                .stream()
//                .map(tableTop -> tableTopMapper.toDTO(tableTop))
//                .collect(Collectors.toList());
//
//
//    }

//    @Override
//    public List<TableTopResult> findAllByStatus(boolean deleted) {
//        return tableTopRepository.findAllByStatus(deleted)
//                .stream()
//                .map(tableTop -> tableTopMapper.toDTO(tableTop))
//                .collect(Collectors.toList());
//    }

    @Override
    public TableTopResult save(TableTopResult tableTopResult) {
        return tableTopMapper.toDTO(tableTopRepository.save(tableTopMapper.toModelResult(tableTopResult)));
    }

    @Override
    public TableTopResult findTableById(Long id) {
        return tableTopMapper.toDTO(tableTopRepository.findById(id).get());
    }

    @Override
    public TableTopResult create(TableTopRegister tableTopRegister) {
        TableTop tableTop = tableTopMapper.toModelRegister(tableTopRegister);
        tableTopRegister.setStatus(tableTop.getStatus());
        tableTopRepository.save(tableTop);
        return tableTopMapper.toDTO(tableTop);
    }


    @Override
    public TableTop findById(Long id) {
        return tableTopRepository.findById(id).get();
    }

    @Override
    public TableTopResult update(TableTop tableTop) {
        return tableTopMapper.toDTO(tableTopRepository.save(tableTop));
    }

    @Override
    public Page<TableTop> findAllpt(Pageable pageable) {
//        pageable= PageRequest.of(1, 10);
        return tableTopRepository.findAll(pageable);
    }

    @Override
    public List<TableTop> findAllPage(int numberPage) {
        return tableTopRepository.findAllPage(numberPage);
    }


}
