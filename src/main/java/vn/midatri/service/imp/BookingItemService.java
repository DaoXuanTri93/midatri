package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.exceptions.NumberFormatException;
import vn.midatri.mapper.BookingItemMapper;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.repository.ItemRepository;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.repository.model.Item;
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
    private ItemRepository itemRepository;

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
    public BookingItemResult findAllByBookingIdAndItemId(long bookingId, long itemId) {
        return bookingItemMapper.toDTO(bookingItemRepository.findByBookingIdAndItemId(bookingId, itemId));
    }

    @Override
    @Transactional
    public void deleteAllByBookingId(long id) {
        bookingItemRepository.deleteAllByBookingId(id);
    }

    @Override
    @Transactional
    public BookingItemResult create(BookingItemCreate param) {
        Optional<Item> itemOption = itemRepository.findById(param.getItemId());
        if (itemOption.isEmpty())
            throw new NotFoundException("NOT FOUND !!!");

        BookingItem bookingItem
                = bookingItemRepository.findByBookingIdAndItemId(param.getBookingId(), param.getItemId());
        if (bookingItem != null) {
            BookingItem bookingItemParam = bookingItemMapper.toModel(param);
            int oldQuantity = bookingItem.getQuantity();
            int newQuantity = oldQuantity + 1;
            bookingItem.setId(bookingItem.getId());
            BigDecimal price = bookingItem.getPrice();
            bookingItem.setPrice(price);
            bookingItem.setQuantity(newQuantity);
            return bookingItemMapper.toDTO(bookingItemParam);
//            throw new NotFoundException("Exits");
        }

        Item item = itemOption.get();
        param.setQuantity(1);
        BookingItem newBookingItem = bookingItemMapper.toModel(param);
        newBookingItem.setPrice(item.getPrice());
        BigDecimal price = newBookingItem.getPrice();
        bookingItem = bookingItemRepository.save(newBookingItem);
        return bookingItemMapper.toDTO(bookingItem);
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
        int quantity = bookingItem.getQuantity();
        if (quantity <= 0) {
            throw new NumberFormatException("Quantity cannot be lower");
        }
        bookingItem.setQuantity(quantity - 1);
        bookingItemRepository.save(bookingItem);
        return bookingItem.getQuantity();
    }


}
