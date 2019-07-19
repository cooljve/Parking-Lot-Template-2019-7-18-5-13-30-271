package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

  @Autowired
  private ParkingLotRepository lotRepository;


  public Page<ParkingLot> findAllParkingLotsWithPagination(int pageNum) {
    Pageable pageable = PageRequest.of(pageNum, 15);
    return lotRepository.findAllParkingLotsWithPagination(pageable);
  }

  public ParkingLot findById(String parkingLotName) {
    return lotRepository.findById(parkingLotName).get();
  }

  public void update(String parkingLotName, ParkingLot parkingLot) {
    parkingLot.setParkingLotName(parkingLotName);
    lotRepository.save(parkingLot);
  }

  public void deleteById(String parkingLotName) {
    lotRepository.deleteById(parkingLotName);
  }

  public void save(ParkingLot parkingLot) {
    lotRepository.save(parkingLot);
  }
}
