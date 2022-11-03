package vn.midatri.service.imp;

import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.mapper.OrderItemMapper;
import vn.midatri.repository.OrderItemRepository;
import vn.midatri.repository.OrderRepository;
import vn.midatri.repository.model.Order;
import vn.midatri.repository.model.OrderItem;
import vn.midatri.service.IOrderItemService;
import vn.midatri.service.IOrderService;

import java.util.Arrays;
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

}
