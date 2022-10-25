package vn.midatri.dto.item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.user.UserResult;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class ItemParam {
    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String img;
    private String content;
    private CategoryResult category;
    private UserResult user;
    private boolean deleted;
}
