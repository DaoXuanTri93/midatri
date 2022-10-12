package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.item.ItemCreate;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByDeleted(Boolean deleted);
    Item save(ItemResult itemResult);

//    @Query("SELECT it FROM Item as it WHERE it.category = :id " )
//    List<Item> findByIdCategory(@Param("category") Long id);
    List<Item> findAllByCategoryId(Long id);


}
