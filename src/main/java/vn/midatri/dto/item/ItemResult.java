package vn.midatri.dto.item;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ItemResult {
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String img;
    private String content;
    private Long category_id;
    private Long user_id;
    private boolean deleted;
}

