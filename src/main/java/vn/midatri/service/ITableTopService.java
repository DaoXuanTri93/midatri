package vn.midatri.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;

import java.util.List;

public interface ITableTopService {
    List<TableTopResult> findAll();
    List<TableTopResult> findAllByDeleted(boolean deleted);
    TableTopResult save(TableTopResult tableTopResult);
    TableTopResult findTableById( Long id);
    TableTopResult create(TableTopRegister tableTopRegister);

    TableTop save1(TableTop tableTop);
    TableTop findById(Long id);
}
