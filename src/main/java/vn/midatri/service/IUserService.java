package vn.midatri.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.repository.model.User;

import java.util.List;

@Service
@Transactional
public interface IUserService {

    List<User> findAllUser();
}
