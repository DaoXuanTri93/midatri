package vn.midatri.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.tableTop.TableTopResult;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.model.BookingStatus;
import vn.midatri.repository.model.TableTop;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingResult {
    private long id;
    private String fullName;
    private BookingStatus status;
    private String phone;
    private String email;
    private String address;
    private String content;
    private Instant createAt;
    private Instant updateAt;
    private long userId;
    private long tableTopId;

}
