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
import java.util.Optional;

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
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserRegister userRegister, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            return AppUtils.mapError(bindingResult);
        }
        UserResult userResult = userService.save(userRegister);
        return new ResponseEntity<>(userResult, HttpStatus.OK);
    }

    @PostMapping("/deleted/{id}")
    public ResponseEntity<?> deletedUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        user.setDeleted(true);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @PutMapping("/edit/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
//        User userCheck = userService.findUserById(id);
//
//        userCheck.setUserName(user.getUserName());
//        userCheck.setPhone(user.getPhone());
//        userCheck.setEmail(user.getEmail());
//
//        UserResult newUser= userService.save(userCheck);
//        return new ResponseEntity<>(newUser, HttpStatus.OK);

@PutMapping("/edit/{id}")
public ResponseEntity editUser(@PathVariable Long id,@RequestBody User user){
        UserResult userResult = userMapper.toDTOEdit(userService.findUserById(id));
       UserResult userResult1 =   userService.Update(userResult,user);
    return new ResponseEntity<>(userResult1,HttpStatus.ACCEPTED);
}
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody User user){
//        U user = userService.findUserById(id);
//        user.setId(user1.getId());
//        userService.update(user);
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

    @GetMapping("/renderDeletedUser")
    public ResponseEntity<?> ListDeletedUser() {
        List<User> users = userService.findAllByDeleted(true);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<?> restoreUser(@PathVariable Long id) {
        User user = userService.findById(id);
        user.setDeleted(false);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
