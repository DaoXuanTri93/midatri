package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.mapper.OrderMapper;
import vn.midatri.repository.OrderItemRepository;
import vn.midatri.repository.OrderRepository;
import vn.midatri.repository.model.Order;
import vn.midatri.repository.model.OrderItem;
import vn.midatri.service.IOrderService;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public OrderResult create(OrderParam orderParam) {
        return orderMapper.toDTO(orderRepository.save(orderMapper.toModel(orderParam)));
    }

    @Override
    public List<OrderResult> findAllByCreateAt(Date createAt) {
        return orderRepository.findAllByCreateAt(createAt)
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .collect(Collectors.toList());

    }
}
