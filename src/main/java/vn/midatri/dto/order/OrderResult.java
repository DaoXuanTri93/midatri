package vn.midatri.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.OrderStatus;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class OrderResult {
    private Long id;
    private OrderStatus status;
    private BigDecimal grandTotal;
    private int totalQuantity;
    private Float discount;
    private String fullName;
    private String phone;
    private String email;
    private String address;
    private String content;
    private String createAt;
    private long userId;

}
