package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByDeleted(boolean deleted);

    User findUserById(Long id);

//    List<UserResult> findAll();

}
