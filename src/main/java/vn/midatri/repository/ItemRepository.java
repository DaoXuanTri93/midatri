package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
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

}
