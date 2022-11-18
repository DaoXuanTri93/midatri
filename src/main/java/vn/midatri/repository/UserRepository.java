package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.TUserDTO;
import vn.midatri.dto.TUserDTO1;
import vn.midatri.dto.user.UserLogin;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByDeleted(boolean deleted);
    User findUserById(Long id);

    User save(UserResult userResult);
//    User update(User user);
    User findByUserNameAndPassword(String userName, String password);

    @Query(value = "SELECT * FROM test_view_user", nativeQuery = true)
    List<TUserDTO> getAllTUserDTO();

    @Query(value = "call test_sp_user()", nativeQuery = true)
    List<TUserDTO1> getAllTUserDTO1();
}
