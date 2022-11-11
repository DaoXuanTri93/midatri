package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.mapper.OrderMapper;
import vn.midatri.repository.OrderRepository;
import vn.midatri.repository.model.Chart;
import vn.midatri.repository.model.Order;
import vn.midatri.service.IOrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
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
    public List<Chart> chartBar() {
        return orderRepository.chartBar();
    }
    public List<OrderResult> findAllByCreateAt(Date createAt) {
        return orderRepository.findAllByCreateAt(createAt)
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .collect(Collectors.toList());
    }



    @Override
    public List<Chart> chartDay() {
        return orderRepository.chartDay();
    }

    @Override
    public List<Chart> chartMonth() {
        return orderRepository.chartMonth();
    }

    @Override
    public List<Chart> chartLastDay() {
        return orderRepository.chartLastDay();
    }

    @Override
    public List<Chart> chartLast7Day() {
        return orderRepository.chartLast7Day();
    }

    @Override
    public List<Chart> chartByMonth() {
        return orderRepository.chartByMonth();
    }

    @Override
    public List<Chart> chartByLastMonth() {
        return orderRepository.chartByLastMonth();
    }

    @Override
    public List<Chart> totalOrderItems() {
        return orderRepository.totalOrderItem();
    }

    @Override
    public List<Chart> totalOneDay() {
        return orderRepository.totalOneDay();
    }

    @Override
    public List<Chart> totalLastDay() {
        return orderRepository.totalLastDay();
    }

    @Override
    public List<Chart> totalLastMonth() {
        return orderRepository.totalLastMonth();
    }

    @Override
    public List<Chart> totalMonth() {
        return orderRepository.totalMonth();
    }

    @Override
    public List<Chart> allTotalToDay() {
        return orderRepository.allTotalToDay();
    }

    @Override
    public List<Chart> allTotalMonth() {
        return orderRepository.allTotalMonth();
    }

    public List<OrderResult> findSevenDay() {
        return orderRepository.findSevenDay()
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResult> findThisMonth(Date createAt) {
        return orderRepository.findThisMonth(createAt)
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderResult> findByCreateAtBetween(Date toDay, Date fromDay) {
        return orderRepository.findByCreateAtBetween(toDay,fromDay)
                .stream()
                .map(order -> orderMapper.toDTO(order))
                .collect(Collectors.toList());
    }
}
