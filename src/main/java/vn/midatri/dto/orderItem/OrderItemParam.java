package vn.midatri.dto.orderItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class OrderItemParam {
    private long itemId;
    private long orderId;
    private BigDecimal price;
    private int quantity;
    private Float discount;
    private Instant createAt;
    private String content;
}
