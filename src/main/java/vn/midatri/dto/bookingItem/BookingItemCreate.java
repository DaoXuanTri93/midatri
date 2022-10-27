package vn.midatri.dto.bookingItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.repository.model.BookingItemStatus;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingItemCreate {
    private Integer quantity;
    private String content;
    private BookingItemStatus status;
    private long bookingId;
    private long itemId;
}
