package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "parking-lot")
public class ParkingLotController {

  @Autowired
  private ParkingLotRepository repository;

  @PostMapping
  public void addParkingLot(@RequestBody ParkingLot parkingLot) {
    repository.save(parkingLot);
  }
}
