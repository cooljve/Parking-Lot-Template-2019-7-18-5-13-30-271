package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "parking-lots")
public class ParkingLotController {

  @Autowired
  private ParkingLotService lotService;

  @PostMapping
  public void addParkingLot(@RequestBody ParkingLot parkingLot) {
    lotService.save(parkingLot);
  }

  @DeleteMapping("{parkingLotName}")
  public void deleteParkingLot(@PathVariable String parkingLotName) {
    lotService.deleteById(parkingLotName);
  }

  @GetMapping(params = "pageNum")
  public Page<ParkingLot> findParkingLotsByPageAndPageSize(@RequestParam int pageNum) {
    return lotService.findAllParkingLotsWithPagination(pageNum);
  }

  @GetMapping("{parkingLotName}")
  public ParkingLot findParkingLotsByPageAndPageSize(@PathVariable String parkingLotName) {
    return lotService.findById(parkingLotName);
  }

  @PutMapping("{parkingLotName}")
  public void updateParkingLot(@PathVariable String parkingLotName, @RequestBody ParkingLot parkingLot) {
    lotService.update(parkingLotName, parkingLot);
  }
}
