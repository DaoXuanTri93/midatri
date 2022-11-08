package vn.midatri.dto.transaction;


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
public class Information {
    private long id;
    private Instant createAt;
    private BigDecimal grandTotal;
    private OrderStatus status;
    private int totalQuantity;
    private long itemId ;
    private String title;
    private int quantity;
    private BigDecimal price;


}
