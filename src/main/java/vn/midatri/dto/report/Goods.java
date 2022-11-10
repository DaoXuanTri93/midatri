package vn.midatri.dto.report;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Accessors(chain = true)
public class Goods {
    private Instant createAt;
    private long itemId ;
    private BigDecimal price;
    private int quantity;
    private String title;


}
