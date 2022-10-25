package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

@Component
public class UserMapper {

    public User toUserRegister(UserRegister userRegister){
        return new User()
        .setUsername(userRegister.getUsername())
        .setPassword(userRegister.getPassword())
        .setEmail(userRegister.getEmail())
        .setPhone(userRegister.getPhone());
    }

    public UserResult toDTO(User user){
        return new UserResult()
        .setId(user.getId())
        .setUserName(user.getUsername())
        .setPassword(user.getPassword())
        .setDeleted(user.getDeleted())
        .setPhone(user.getPhone())
        .setEmail(user.getEmail());

    }
    public User toModel(UserResult userResult){
        return new User()
                .setId(userResult.getId())
                .setUsername(userResult.getUserName())
                .setPassword(userResult.getPassword())
                .setDeleted(userResult.getDeleted())
                .setPhone(userResult.getPhone())
                .setEmail(userResult.getEmail());
    }
}
