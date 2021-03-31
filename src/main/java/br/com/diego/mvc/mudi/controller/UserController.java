package br.com.diego.mvc.mudi.controller;

import br.com.diego.mvc.mudi.model.Order;
import br.com.diego.mvc.mudi.model.OrderStatus;
import br.com.diego.mvc.mudi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("orders")
    public String home(Model model, Principal principal) {
        List<Order> orders = orderRepository.findAllByUser(principal.getName());
        model.addAttribute("orders", orders);

        return "user/home";
    }

    @GetMapping("orders/{status}")
    public String status(@PathVariable("status") String status, Model model, Principal principal) {
        List<Order> orders = orderRepository.findByStatusAndUser(OrderStatus.valueOf(status.toUpperCase()), principal.getName());
        model.addAttribute("orders", orders);
        model.addAttribute("status", status);

        return "user/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/user/home";
    }
}
