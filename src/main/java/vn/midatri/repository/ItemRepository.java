package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByDeleted(Boolean deleted);
    Item save(Item item);
    List<Item> findAllByCategoryId(Long id);
    Optional<Item> findById(Long id);

    List<Item> findAllByCategory_ParentId(long parenId);

    @Query("SELECT i " +
                "FROM Item AS i " +
                "JOIN Category AS c " +
                "ON i.categoryId = c.id " +
            "WHERE " +
                "(c.parentId IN (:parentIds)  OR (:parentIds) IS NULL) " +
                "AND (i.categoryId = :childId OR :childId IS NULL) " +
                "AND (i.deleted = :status OR :status IS NULL)")
    List<Item> findAllByCategory(@Param("parentIds") List<Long> parentIds,
                                 @Param("childId") Long childId,
                                 @Param("status") boolean status);

}
