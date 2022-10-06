package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import vn.midatri.repository.UserRepository;
import vn.midatri.repository.model.User;
import vn.midatri.service.IUserService;

import java.util.List;

public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }


}
