package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.user.UserRegister;
import vn.midatri.dto.user.UserResult;
import vn.midatri.mapper.UserMapper;
import vn.midatri.repository.model.User;
import vn.midatri.service.IUserService;
import vn.midatri.util.AppUtils;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApi {
    @Autowired
    IUserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<?> renderUser() {

        List<User> users = userService.findAllByDeleted(false);
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        UserResult userResult = userService.findById(id);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserRegister userRegister, BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            return AppUtils.mapError(bindingResult);
        }
        UserResult userResult = userService.save(userRegister);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @PostMapping("/deleted/{id}")
    public ResponseEntity<?> deletedUser(@PathVariable Long id){
        User user = userService.findUserById(id);
        user.setDeleted(true);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping ("/edit/{id}")

//    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user) {
//        UserResult userResult = userMapper.toDTO(userService.findUserById(id));
//        user.setId(user.getId());
 ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
//        UserResult userResult = userMapper.toDTO(userService.findUserById(id));
        user.setId(id);

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
