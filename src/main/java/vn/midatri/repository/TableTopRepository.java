package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.repository.model.TableTop;

import java.util.List;

@Repository
public interface TableTopRepository extends JpaRepository<TableTop, Long> {
    List<TableTop> findAll();

    List<TableTop> findAllByDeleted(boolean deleted);
    TableTop save(TableTopResult tableTopResult);
}
