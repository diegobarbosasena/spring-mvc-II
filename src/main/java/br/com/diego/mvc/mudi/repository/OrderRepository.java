package br.com.diego.mvc.mudi.repository;

import br.com.diego.mvc.mudi.model.Order;
import br.com.diego.mvc.mudi.model.OrderStatus;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Cacheable("orders")
    List<Order> findByStatus(OrderStatus status, Pageable pageable);

    @Query("select o from Order o join o.user u where u.username = :username")
    List<Order> findAllByUser(@Param("username") String username);

    @Query("select o from Order o join o.user u where u.username = :username and o.status = :status")
    List<Order> findByStatusAndUser(@Param("status") OrderStatus status, @Param("username") String username);
}
