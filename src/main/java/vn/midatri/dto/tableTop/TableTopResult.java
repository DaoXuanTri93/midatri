package vn.midatri.dto.tableTop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class TableTopResult {
    private Long id;
    private String code;
    private Short status;
    private Short capacity;
    private String content;
    private Boolean deleted;
}
