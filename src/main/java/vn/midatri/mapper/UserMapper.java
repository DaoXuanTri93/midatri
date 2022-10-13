package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

@Component
public class UserMapper {

    public User toUserRegister(UserRegister userRegister){
        User user = new User();
        user.setUserName(userRegister.getUsername());
        user.setPassword(userRegister.getPassword());
        user.setEmail(userRegister.getEmail());
        user.setPhone(userRegister.getPhone());
        return user;
    }

    public UserResult toDTO(User user){
        UserResult userResult = new UserResult();
        userResult.setId(user.getId());
        userResult.setUserName(user.getUserName());
        userResult.setPassword(user.getPassword());
        userResult.setDeleted(user.getDeleted());
        return userResult;
    }

    public User toUser(UserResult userResult){
        User user = new User();
        user.setId(userResult.getId());
        user.setUserName(userResult.getUserName());
        user.setPassword(userResult.getPassword());
        user.setDeleted(userResult.getDeleted());
        return user;
    }


}
