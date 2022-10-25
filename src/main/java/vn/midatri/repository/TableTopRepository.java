package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.TableTop;

import java.util.List;

@Repository
public interface TableTopRepository extends JpaRepository<TableTop, Long> {
    List<TableTop> findAllByStatus(boolean deleted);
    TableTop save(TableTop tableTop);
}
