package vn.midatri.dto.item;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.user.UserResult;


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
    private CategoryResult categoryResult;
    private UserResult userResult;

}

