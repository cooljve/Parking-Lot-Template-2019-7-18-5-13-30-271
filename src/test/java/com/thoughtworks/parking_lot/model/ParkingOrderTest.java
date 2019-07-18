package com.thoughtworks.parking_lot.model;

import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ParkingOrderTest {

  @Autowired
  private ParkingOrderRepository orderRepository;

  @Test
  public void shoule_save_parking_lot() {
    String carNumber = "粤A12345";
    ParkingOrder order = expectParkingOrder(carNumber);
    orderRepository.save(order);
    ParkingOrder order1 = expectParkingOrder(carNumber);
    order1.setStatus(false);
    ParkingOrder order2 = expectParkingOrder("粤B12345");
    ParkingOrder order3 = expectParkingOrder("粤C12345");
    orderRepository.save(order1);
    orderRepository.save(order2);
    orderRepository.save(order3);

    ParkingOrder fetchedOrder = orderRepository.findByCarNumberAndStatusTrue(carNumber);

    assertThat(fetchedOrder).isEqualTo(order);
  }

  private ParkingOrder expectParkingOrder(String carNumber) {
    ParkingOrder order = new ParkingOrder();
    order.setCarNumber(carNumber);
    order.setCreateTime(new Date());
    order.setEndTime(new Date());
    return order;
  }

}