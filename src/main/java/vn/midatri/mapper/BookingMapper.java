package vn.midatri.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.repository.model.Booking;

@Component
public class BookingMapper {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TableTopMapper tableTopMapper;


    public BookingResult toDTO(Booking booking){
        BookingResult bookingResult = new BookingResult();
        bookingResult.setId(booking.getId());
        bookingResult.setFullName(booking.getFullName());
        bookingResult.setStatus(booking.getStatus());
        bookingResult.setPhone(booking.getPhone());
        bookingResult.setEmail(booking.getEmail());
        bookingResult.setAddress(booking.getAddress());
        bookingResult.setContent(booking.getContent());
        bookingResult.setUserResult(userMapper.toDTO(booking.getUser()));
        bookingResult.setTableTopResult(tableTopMapper.toDTO(booking.getTableTop()));
        return bookingResult;
    }
}
