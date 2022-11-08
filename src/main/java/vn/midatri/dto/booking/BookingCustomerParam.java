package vn.midatri.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Parent;
import vn.midatri.repository.model.BookingStatus;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingCustomerParam {

    private Long id;

    @NotBlank(message = "Xin vui lòng nhập tên !!!" )
    private String fullName;

    @NotBlank(message = "Xin vui lòng nhập số điện thoại !!!" )
    private String phone;

    @Email(message = "Email không đúng định dạng !!!")
    private String email;


    private String address;

    private String content;
    private Instant createAt;
    private Instant updateAt;

}
