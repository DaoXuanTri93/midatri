package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.bookingItem.BookingItemAdd;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;

@Component
public class BookingItemMapper {
    @Autowired
    ItemMapper itemMapper;

    @Autowired
    BookingMapper bookingMapper;

    public BookingItem toModel(BookingItemAdd bookingItemAdd) {
        BookingItem bookingItem = new BookingItem();
        bookingItem.setId(bookingItemAdd.getId());
        bookingItem.setPrice(bookingItemAdd.getPrice());
        bookingItem.setQuantity(bookingItemAdd.getQuantity());
        return bookingItem;
    }

    public BookingItemResult toDTO(BookingItem bookingItem) {
        BookingItemResult bookingItemResult = new BookingItemResult();
        bookingItemResult.setId(bookingItem.getId());
        bookingItemResult.setBookingResult(bookingMapper.toDTO(bookingItem.getBooking()));
        bookingItemResult.setItemResult(itemMapper.toDTO(bookingItem.getItem()));
        bookingItemResult.setPrice(bookingItem.getPrice());
        bookingItemResult.setQuantity(bookingItem.getQuantity());
        bookingItemResult.setDiscount(bookingItem.getDiscount());
        bookingItemResult.setStatus(bookingItem.getStatus());
        bookingItemResult.setContent(bookingItem.getContent());
        bookingItemResult.setGrandTotal(bookingItem.getGrandTotal());
        return bookingItemResult;
    }

}
