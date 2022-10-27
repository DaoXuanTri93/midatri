package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.mapper.UserMapper;
import vn.midatri.repository.UserRepository;
import vn.midatri.repository.model.User;
import vn.midatri.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserResult> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.toDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserResult findById(Long id) {
        return userMapper.toDTO(userRepository.findById(id).get());
    }

    @Override
    public UserResult create(UserRegister userRegister) {
//         return userMapper.toDTO(userRepository.save(userMapper.toDTO()));
        User user = userMapper.toUserRegister(userRegister);

        userRepository.save(user);

        return userMapper.toDTO(user);
    }

    @Override
    public void deletedById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllByDeleted(boolean deleted) {
        return userRepository.findAllByDeleted(deleted);
    }

    @Override
    public UserResult Update(UserResult userResult, User user) {
        user.setId(userResult.getId());
        user.setPassword(userResult.getPassword());
        UserResult userResults = userMapper.toDTO(userRepository.save(user));
        user.setPassword("null");
        return userResults;
    }

    @Override
    public UserResult save(User user) {
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}
