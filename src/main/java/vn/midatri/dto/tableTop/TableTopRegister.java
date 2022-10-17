package vn.midatri.dto.tableTop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class TableTopRegister {
    private Long id;
    private String code;
    private Short status;
    private Short capacity;
    private String content;
}
