package vn.midatri.dto.item;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.user.UserResult;
import vn.midatri.repository.UserRepository;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.User;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Accessors(chain = true)
public class CreateItem {
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private String img;
    private String content;
    private long category;
    private long user;
    private boolean deleted ;



}
