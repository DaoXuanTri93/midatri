package vn.midatri.dto.item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.User;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Accessors(chain = true)
public class ItemCreate {
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String img;
    private String content;
    private Category category_id;
    private User user_id;
    private boolean deleted ;



}
