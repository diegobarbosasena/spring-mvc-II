package br.com.diego.mvc.mudi.api;

import br.com.diego.mvc.mudi.dto.RequestNewOffer;
import br.com.diego.mvc.mudi.model.Offer;
import br.com.diego.mvc.mudi.model.Order;
import br.com.diego.mvc.mudi.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/offer")
public class OfferRest {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody RequestNewOffer request) {

        Optional<Order> responseOrder = orderRepository.findById(request.getOrderId());

        if (responseOrder.isEmpty()) return null;

        Order order = responseOrder.get();

        Offer newOffer = request.toOffer();
        newOffer.setOrder(order);
        order.getOffers().add(newOffer);

        orderRepository.save(order);

        return ResponseEntity.ok().body(newOffer);
    }
}
