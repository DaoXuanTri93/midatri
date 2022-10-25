package vn.midatri.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class UserResult {
    private Long id;
    private String userName;
    private String password;
    private String phone;
    private String email;

}
