package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.dto.report.Goods;
import vn.midatri.mapper.OrderItemMapper;
import vn.midatri.repository.OrderItemRepository;
import vn.midatri.service.IOrderItemService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderItemService implements IOrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;


    @Override
    public OrderItemResult create(OrderItemParam orderItemParam) {
        return orderItemMapper.toDTO(orderItemRepository.save(orderItemMapper.toModel(orderItemParam)));
    }

    @Override
    public long findMaxId(){
        return orderItemRepository.findMaxId();
    }

    @Override
    public List<OrderItemResult> findAllByOrderId(Long id) {
        return orderItemRepository.findAllByOrderId(id)
                .stream()
                .map(orderItem -> orderItemMapper.toDTO(orderItem))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemResult> findAll() {
        return orderItemRepository.findAll()
                .stream()
                .map(orderItem -> orderItemMapper.toDTO(orderItem))
                .collect(Collectors.toList());
    }

    @Override
    public List<Goods> findAllByToDay() {
        return orderItemRepository.findAllByToDay();
    }

    @Override
    public List<Goods> findAllByLastDay() {
        return orderItemRepository.findAllByLastDay();
    }

    @Override
    public List<Goods> findAllByToMonth() {
        return orderItemRepository.findAllByToMonth();
    }

    @Override
    public List<Goods> findAllByLastMonth() {
        return orderItemRepository.findAllByLastMonth();
    }

    @Override
    public List<Goods> findAllBySevenDay() {
        return orderItemRepository.findAllBySevenDay();
    }

    @Override
    public List<Goods> findAllByCreateAtBetween(Date ToDay, Date FromDay) {
        return orderItemRepository.findAllByCreateAtBetween(ToDay, FromDay);
    }

}
