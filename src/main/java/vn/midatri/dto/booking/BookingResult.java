package vn.midatri.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.TableTop;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingResult {
    private Long id;
    private String fullName;
    private Short status;
    private String phone;
    private String email;
    private String address;
    private String content;
    private UserResult userResult;
    private TableTopResult tableTopResult;

}
