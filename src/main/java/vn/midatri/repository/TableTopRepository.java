package vn.midatri.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.TableTop;
import vn.midatri.repository.model.TabletopStatus;

import java.util.List;

@Repository
public interface TableTopRepository extends JpaRepository<TableTop, Long> {
    List<TableTop> findAllByStatus(TabletopStatus status);

    //    TableTop save(TableTop tableTop);
    List<TableTop> findAllByStatusNot(TabletopStatus status);

    @Query("SELECT t FROM TableTop t")
    Page<TableTop> findAll(Pageable pageable);

    @Query( nativeQuery = true, value = "CALL renderPageTable(:numberPage);")
    List<TableTop> findAllPage(@Param("numberPage") int numberPage);

}
