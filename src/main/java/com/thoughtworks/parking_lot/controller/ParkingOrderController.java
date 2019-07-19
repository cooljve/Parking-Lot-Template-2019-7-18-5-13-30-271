package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.service.ParkingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "parking-orders")
public class ParkingOrderController {

  @Autowired
  private ParkingOrderService orderService;

  @PostMapping(params = {"parkingLotName", "carNumber"})
  public String createParkingOrder(@RequestParam String parkingLotName, @RequestParam String carNumber) {
    return orderService.createParkingOrder(parkingLotName, carNumber);
  }

  @PutMapping(params = "carNumber")
  public void fetchCar(@RequestParam String carNumber) {
    orderService.fetchCar(carNumber);
  }

}
