package br.com.diego.mvc.mudi.controller;

import br.com.diego.mvc.mudi.model.Order;
import br.com.diego.mvc.mudi.model.OrderStatus;
import br.com.diego.mvc.mudi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String home(Model model) {
        Sort sort = Sort.by("deliveryDate").descending();

        PageRequest pageRequest = PageRequest.of(0, 10, sort);

        List<Order> orders = orderRepository.findByStatus(OrderStatus.DELIVERED, pageRequest);
        model.addAttribute("orders", orders);

        return "home";
    }
}
