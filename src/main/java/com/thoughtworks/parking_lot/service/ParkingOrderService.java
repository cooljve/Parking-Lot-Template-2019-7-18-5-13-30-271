package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ParkingOrderService {
  @Autowired
  private ParkingOrderRepository orderRepository;

  @Autowired
  private ParkingLotRepository lotRepository;

  public String createParkingOrder(String parkingLotName, String carNumber) {
    ParkingLot parkingLot = lotRepository.findById(parkingLotName).get();
    boolean isFull = parkingLot.getCapacity() <= parkingLot.getParkingOrders().size();
    if (!isFull) {
      ParkingOrder order = new ParkingOrder();
      order.setCarNumber(carNumber);
      order.setCreateTime(new Date());
      orderRepository.save(order);
      return "成功停车";
    } else return "该停车场已停满";
  }

  public void fetchCar(String carNumber) {
    ParkingOrder order = orderRepository.findByCarNumberAndStatusTrue(carNumber);
    order.setEndTime(new Date());
    order.setStatus(false);
    orderRepository.save(order);
  }
}
