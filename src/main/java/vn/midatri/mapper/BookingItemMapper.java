package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Booking;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.repository.model.Item;

@Component
public class BookingItemMapper {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    BookingMapper bookingMapper;

    public BookingItemResult toDTO(BookingItem bookingItem) {
        BookingResult booking = bookingMapper.toDTO(bookingItem.getBooking());
        ItemResult item = itemMapper.toDTO(bookingItem.getItem());
        return new BookingItemResult()
                .setId(bookingItem.getId())
                .setBooking(booking)
                .setItem(item)
                .setPrice(bookingItem.getPrice())
                .setQuantity(bookingItem.getQuantity())
                .setDiscount(bookingItem.getDiscount())
                .setStatus(bookingItem.getStatus())
                .setContent(bookingItem.getContent())
                .setGrandTotal(bookingItem.getGrandTotal());
    }

    public BookingItem toModel(BookingItemCreate createParam) {
        Booking booking = new Booking(createParam.getBookingId());
        Item item = new Item(createParam.getItemId());
        return new BookingItem()
                .setContent(createParam.getContent())
                .setQuantity(createParam.getQuantity())
                .setBooking(booking)
                .setItem(item);
    }


}
