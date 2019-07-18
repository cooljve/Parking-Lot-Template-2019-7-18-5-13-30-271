package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RequestMapping(value = "parking-orders")
@RestController
public class ParkingOrderController {

  @Autowired
  private ParkingOrderRepository orderReoisitory;

  @Autowired
  private ParkingLotRepository lotRepository;

  @PostMapping(params = {"parkingLotName", "carNumber"})
  public String createParkingOrder(@RequestParam String parkingLotName, @RequestParam String carNumber) {
    ParkingLot parkingLot = lotRepository.findById(parkingLotName).get();
    boolean isFull = parkingLot.getCapacity() <= parkingLot.getParkingOrders().size();
    if (!isFull) {
      ParkingOrder order = new ParkingOrder();
      order.setCarNumber(carNumber);
      order.setCreateTime(new Date());
      orderReoisitory.save(order);
      return "成功停车";
    } else return "该停车场已停满";
  }

  @PutMapping(params = "carNumber")
  public void fetchCar(@RequestParam String carNumber) {
    ParkingOrder order = orderReoisitory.findByCarNumberAndStatusTrue(carNumber);
    order.setEndTime(new Date());
    order.setStatus(false);
    orderReoisitory.save(order);
  }

}
