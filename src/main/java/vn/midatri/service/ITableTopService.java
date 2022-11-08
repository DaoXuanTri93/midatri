package vn.midatri.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;
import vn.midatri.repository.model.TabletopStatus;

import java.util.List;

public interface ITableTopService {
    List<TableTopResult> findAll();

    List<TableTopResult> findAllByStatus(TabletopStatus status);


    //    List<TableTopResult> findAllByList(TabletopStatus status);
    List<TableTopResult> findAllByStatusNot(TabletopStatus status);

    TableTopResult save(TableTopResult tableTopResult);

    TableTopResult findTableById(Long id);

    TableTopResult create(TableTopRegister tableTopRegister);

    TableTop findById(Long id);

    TableTopResult update(TableTop tableTop);

    Page<TableTop> findAllpt(Pageable pageable);
}
