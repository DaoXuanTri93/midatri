package vn.midatri.dto.tableTop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableTopResult {
    private Long id;
    private String code;
    private Short status;
    private Short capacity;
    private String content;
    private Boolean deleted;
}
