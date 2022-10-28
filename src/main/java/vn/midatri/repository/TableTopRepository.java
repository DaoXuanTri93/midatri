package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.TableTop;
import vn.midatri.repository.model.TabletopStatus;

import java.util.List;

@Repository
public interface TableTopRepository extends JpaRepository<TableTop, Long> {
    List<TableTop> findAllByStatus(TabletopStatus status);
    TableTop save(TableTop tableTop);
    List<TableTop> findAllByStatusNot(TabletopStatus status);
}
