package vn.midatri.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.midatri.dto.tableTop.TableTopRegister;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;

import java.util.List;

public interface ITableTopService {
    List<TableTop> findAll();
    List<TableTop> findAllByDeleted(boolean deleted);
    TableTop save(TableTop tableTop);
}
