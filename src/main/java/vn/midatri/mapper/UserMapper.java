package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.User;

@Component
public class UserMapper {

    public User toUserRegister(UserRegister userRegister) {
        return new User()
                .setUserName(userRegister.getUserName())
                .setFullName(userRegister.getFullName())
                .setPassword(userRegister.getPassword())
                .setEmail(userRegister.getEmail())
                .setPhone(userRegister.getPhone());
    }

    public UserResult toDTO(User user) {
        return new UserResult()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setPassword(user.getPassword())
                .setFullName(user.getFullName())
                .setPhone(user.getPhone())
                .setDeleted(user.getDeleted())
                .setVendor(user.getVendor())
                .setBartender(user.getBartender())
                .setEmail(user.getEmail());

    }

//    public User toModel(UserResult userResult) {
//        return new User()
//                .setId(userResult.getId())
//                .setUsername(userResult.getUserName())
//                .setPhone(userResult.getPhone())
//                .setEmail(userResult.getEmail());
//    }

//    public UserResult toDTOEdit(User user) {
//        return new UserResult()
//                .setId(user.getId())
//                .setUserName(user.getUsername())
//                .setFullName(user.getFullName())
//                .setPassword(user.getPassword())
//                .setEmail(user.getEmail())
//                .setPhone(user.getPhone());
//    }
}
