package vn.midatri.dto.bookingItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.BookingItemStatus;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class BookingItemResult {
    private Long id;
    private BigDecimal price;
    private Integer quantity;
    private Float discount;
    private BookingItemStatus status;
    private String content;
    private long itemId;
    private long bookingId;
}
