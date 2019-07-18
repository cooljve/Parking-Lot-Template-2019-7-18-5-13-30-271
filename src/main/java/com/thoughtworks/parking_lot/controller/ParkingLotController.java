package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "parking-lots")
public class ParkingLotController {

  @Autowired
  private ParkingLotRepository repository;

  @PostMapping
  public void addParkingLot(@RequestBody ParkingLot parkingLot) {
    repository.save(parkingLot);
  }

  @DeleteMapping("{parkingLotName}")
  public void deleteParkingLot(@PathVariable String parkingLotName) {
    repository.deleteById(parkingLotName);
  }

  @GetMapping(params = "pageNum")
  public void findParkingLotsByPageAndPageSize(@RequestParam int pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 15);
    repository.findAllParkingLotsWithPagination(pageable);
  }
}
