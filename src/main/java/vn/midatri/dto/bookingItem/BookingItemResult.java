package vn.midatri.dto.bookingItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.item.ItemResult;

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
    private Short status;
    private String content;
    private BigDecimal grandTotal;
    private ItemResult item;
    private BookingResult booking;
}
