package br.com.diego.mvc.mudi.controller;

import br.com.diego.mvc.mudi.dto.RequestOrder;
import br.com.diego.mvc.mudi.model.Order;
import br.com.diego.mvc.mudi.model.User;
import br.com.diego.mvc.mudi.repository.OrderRepository;
import br.com.diego.mvc.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("create")
    public String create(RequestOrder request) {
        return "order/create";
    }

    @PostMapping("save")
    public String save(@Valid RequestOrder request, BindingResult result) {
        if (result.hasErrors())
            return "order/create";

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);

        Order order = request.toOrder();
        order.setUser(user);
        orderRepository.save(order);

        return "redirect:/home";
    }
}
