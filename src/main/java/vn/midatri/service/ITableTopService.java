package vn.midatri.service;

import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;
import vn.midatri.repository.model.TabletopStatus;

import java.util.List;

public interface ITableTopService {
    List<TableTopResult> findAll();
    List<TableTopResult> findAllByStatus(TabletopStatus status);
    TableTopResult save(TableTopResult tableTopResult);
    TableTopResult findTableById( Long id);
    TableTopResult create(TableTopRegister tableTopRegister);
    TableTop findById(Long id);
    TableTopResult update(TableTop tableTop);
}
