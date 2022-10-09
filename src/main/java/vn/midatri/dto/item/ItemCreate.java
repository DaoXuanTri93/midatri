package vn.midatri.dto.item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.User;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemCreate {
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String img;
    private String content;
    private Category category_id;
    private User user_id;
}
