package vn.midatri.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CategoryParam {
    private Long id;
    private String categoryName;
    private Long parentId;
    private CategoryResult parent;
}
