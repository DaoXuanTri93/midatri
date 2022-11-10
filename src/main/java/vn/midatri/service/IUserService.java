package vn.midatri.service;

import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

import java.util.List;
import java.util.Optional;


public interface IUserService {

    List<UserResult> findAll();
    UserResult findById(Long id);
    UserResult create(UserRegister userRegister);

    void deletedById(Long id);
    List<User> findAllByDeleted(boolean deleted);
    UserResult Update(UserResult userResult,User user);

    UserResult save(User user);
    User findUserById(Long id);

   User findByUserNameAndPassword(String userName, String password);


}
