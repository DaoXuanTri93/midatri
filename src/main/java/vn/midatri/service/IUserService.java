package vn.midatri.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

import java.util.List;


public interface IUserService {

    List<UserResult> findAll();
    UserResult findById(Long id);
    UserResult save(UserRegister userRegister);

    void deletedById(Long id);
    List<User> findAllByDeleted(boolean deleted);
    UserResult save(User user);
    User findUserById(Long id);
}
