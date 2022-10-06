package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
