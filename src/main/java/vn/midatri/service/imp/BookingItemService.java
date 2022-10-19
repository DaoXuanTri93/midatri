package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.mapper.BookingItemMapper;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.service.IBookingItemService;
import vn.midatri.service.IItemService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class BookingItemService implements IBookingItemService {
    @Autowired
    private BookingItemRepository bookingItemRepository;

    @Autowired
    private BookingItemMapper bookingItemMapper;

    @Autowired
    private IItemService itemService;

    @Override
    @Transactional(readOnly = true)
    public List<BookingItemResult> findAll() {
        return bookingItemRepository.findAll()
                .stream()
                .map(bookingItem -> bookingItemMapper.toDTO(bookingItem))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingItemResult> findAllByBookingId(Long bookingId) {
        return bookingItemRepository.findAllByBookingId(bookingId)
                .stream()
                .map(bookingItem -> bookingItemMapper.toDTO(bookingItem))
                .collect(Collectors.toList());
    }

    @Override
    public BookingItemResult findByBookingIdAndItemId(long bookingId, long itemId) {
        return bookingItemMapper.toDTO(bookingItemRepository.findByBookingIdAndItemId(bookingId, itemId));
    }


    @Override
    @Transactional
    public BookingItemResult create(BookingItemCreate bookingItemCreate) {
        ItemResult item = itemService.findById(bookingItemCreate.getItem_id());
        BookingItemResult bookingItemResult
                = findByBookingIdAndItemId(bookingItemCreate.getBooking_id(), bookingItemCreate.getItem_id());
        if (bookingItemResult == null) {
            bookingItemCreate.setId(0L);
            bookingItemCreate.setQuantity(1);
            bookingItemCreate.setPrice(item.getPrice());
            BigDecimal price = bookingItemCreate.getPrice();
            bookingItemCreate.setGrandTotal(price.multiply(new BigDecimal(bookingItemCreate.getQuantity())));
            bookingItemCreate.setDiscount(1f);
            return bookingItemMapper.toDTO(bookingItemRepository.save(bookingItemMapper.toModel(bookingItemCreate)));
        }

        int oldQuantity = bookingItemResult.getQuantity();
        int newQuantity = oldQuantity + 1;
        bookingItemCreate.setId(bookingItemResult.getId());
        BigDecimal price = bookingItemResult.getPrice();
        bookingItemCreate.setPrice(price);
        bookingItemCreate.setGrandTotal(price.multiply(new BigDecimal(newQuantity)));
        bookingItemCreate.setQuantity(newQuantity);
        return bookingItemMapper.toDTO(bookingItemRepository.save(bookingItemMapper.toModel(bookingItemCreate)));
    }

    @Override
    @Transactional(readOnly = true)
    public BookingItemResult findById(Long id) {
        return bookingItemMapper.toDTO(bookingItemRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deletedBookingItem(Long id) {
        bookingItemRepository.deleteById(id);
    }

    @Override
    public int increaseQuantity(long id, int quantity) {
        return 0;
    }

    //    @Override
//    @Transactional
//    public int increaseQuantity(long id) {
//        Optional<BookingItem> optional = bookingItemRepository.findById(id);
//        if (optional.isEmpty())
//            throw new NotFoundException("BookingItem not found");
//        BookingItem bookingItem = optional.get();
//        bookingItem.setQuantity(bookingItem.getQuantity()+quantity);
//        bookingItemRepository.save(bookingItem);
//        return  bookingItem.getQuantity();
//    }
    @Override
    @Transactional
    public int increaseQuantity(long id) {
        Optional<BookingItem> optional = bookingItemRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("BookingItem not found");
        BookingItem bookingItem = optional.get();
        bookingItem.setQuantity(bookingItem.getQuantity() + 1);
        bookingItemRepository.save(bookingItem);
        return bookingItem.getQuantity();
    }

    @Override
    @Transactional
    public int reduceQuantity(long id) {
        Optional<BookingItem> optional = bookingItemRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("BookingItem not found");
        BookingItem bookingItem = optional.get();
        bookingItem.setQuantity(bookingItem.getQuantity() - 1);
        bookingItemRepository.save(bookingItem);
        return bookingItem.getQuantity();
    }


}
