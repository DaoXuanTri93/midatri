package vn.midatri.dto.category;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.Category;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CategoryResult {
    private Long id;
    private String categoryName;
    private Long parentId;

}
