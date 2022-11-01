package vn.midatri.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.BookingStatus;

import javax.persistence.Column;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingCustomerParam {
    private Long id;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String content;
    private Instant createAt;
    private Instant updateAt;

}
