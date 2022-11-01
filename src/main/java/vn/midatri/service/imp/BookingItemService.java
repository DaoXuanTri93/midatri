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
import vn.midatri.repository.BookingRepository;
import vn.midatri.repository.ItemRepository;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.repository.model.BookingItemStatus;
import vn.midatri.repository.model.Item;
import vn.midatri.repository.TableTopRepository;
import vn.midatri.repository.model.*;
import vn.midatri.service.IBookingItemService;

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
    private BookingRepository bookingRepository;

    @Autowired
    private TableTopRepository tableTopRepository;

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
    public void deleteAllByBookingId(long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isEmpty()){
            throw new NotFoundException("Not Found");
        }
        Booking booking = bookingOptional.get();

        Optional<TableTop> tableTopOptional = tableTopRepository.findById(booking.getTableTopId());
        if (tableTopOptional.isEmpty()){
            throw new NotFoundException("Not Found");
        }
        TableTop tableTop = tableTopOptional.get();
        tableTop.setStatus(TabletopStatus.AVAILABLE);
        bookingItemRepository.deleteAllByBookingId(bookingId);
        bookingRepository.deleteById(bookingId);

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
            throw new NotFoundException("NOT FOUND BookingItem !!!");
        }

        Item item = itemOption.get();
        BookingItem newBookingItem = bookingItemMapper.toModel(param);
        newBookingItem.setStatus(BookingItemStatus.NEW);
        newBookingItem.setPrice(item.getPrice());
        bookingItem = bookingItemRepository.save(newBookingItem);
        return bookingItemMapper.toDTO(bookingItem);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingItemResult findById(Long id) {
        Optional<BookingItem> bookingItemOptional = bookingItemRepository.findById(id);
        if (bookingItemOptional.isEmpty()){
            throw new NotFoundException("khong tim thay ID : " + id);
        }
        BookingItem bookingItem = bookingItemOptional.get();
        return bookingItemMapper.toDTO(bookingItem);
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
        int quantity = bookingItem.getQuantity();
        bookingItem.setQuantity(quantity + 1);
        bookingItemRepository.save(bookingItem);
        return bookingItem.getQuantity();
    }

    @Override
    @Transactional
    public int decreaseQuantity(long id) {
        Optional<BookingItem> optional = bookingItemRepository.findById(id);

        if (optional.isEmpty())
            throw new NotFoundException("BookingItem not found");

        BookingItem bookingItem = optional.get();
        int quantity = bookingItem.getQuantity();
        bookingItem.setQuantity(quantity - 1);
        if (quantity <= 1) {
            throw new NumberFormatException("Quantity cannot be lower");
        }
        bookingItemRepository.save(bookingItem);
        return bookingItem.getQuantity();
    }

    @Override
    @Transactional
    public int updateQuantity(Long id, int quantity) {
        Optional<BookingItem> optional = bookingItemRepository.findById(id);
        if (optional.isEmpty())
            throw new NotFoundException("BookingItem not found");
        BookingItem bookingItem = optional.get();
        bookingItem.setQuantity(quantity);
        bookingItemRepository.save(bookingItem);
        return bookingItem.getQuantity();
    }

    @Override
    @Transactional
    public String updateNote(Long id, String content) {
        Optional<BookingItem> bookingItemOptional = bookingItemRepository.findById(id);
        if (bookingItemOptional.isEmpty()){
            throw new NotFoundException("Note ko thành công");
        }
        BookingItem bookingItem = bookingItemOptional.get();
        bookingItem.setContent(content);
        bookingItemRepository.save(bookingItem);
        return bookingItem.getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingItemResult> findAllByStatus(BookingItemStatus status) {
        return bookingItemRepository.findAllByStatus(status)
                .stream()
                .map(bookingItem -> bookingItemMapper.toDTO(bookingItem))
                .collect(Collectors.toList());
    }


}
