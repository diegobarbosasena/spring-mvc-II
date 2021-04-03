package br.com.diego.mvc.mudi.api;

import br.com.diego.mvc.mudi.model.Order;
import br.com.diego.mvc.mudi.model.OrderStatus;
import br.com.diego.mvc.mudi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRest {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("waiting")
    public List<Order> getOrdersAwaitingOffers() {
        Sort sort = Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        return orderRepository.findByStatus(OrderStatus.WAITING, pageRequest);
    }
}
