//package vn.midatri.service.imp;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import vn.midatri.repository.BookingItemRepository;
//import vn.midatri.repository.model.BookingItem;
//import vn.midatri.service.IBookingItemService;
//
//import java.util.List;
//
//@Service
//@Transactional
//public class BookingItemService implements IBookingItemService {
//    @Autowired
//    private BookingItemRepository bookingItemRepository;
//    @Override
//    public List<BookingItem> findAll() {
//        return bookingItemRepository.findAll();
//    }
//
//    @Override
//    public BookingItem addBookingItem(BookingItem bookingItem) {
//        return bookingItemRepository.save(bookingItem);
//    }
//}
