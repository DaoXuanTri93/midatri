package vn.midatri.dto.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegister {
    @NotBlank(message = "Khong duoc bo trong UserName")
    private String userName;
    @NotBlank(message = "Khong duoc bo trong password")
    private String password;
}
