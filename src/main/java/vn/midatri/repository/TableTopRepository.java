package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.TableTop;

import java.util.List;

@Repository
public interface TableTopRepository extends JpaRepository<TableTop, Long> {
    List<TableTop> findAllByDeleted(Boolean status);
    List<TableTop> findAll();

    List<TableTop> findAllByStatus(boolean status);
    TableTop save(TableTop tableTop);
}
