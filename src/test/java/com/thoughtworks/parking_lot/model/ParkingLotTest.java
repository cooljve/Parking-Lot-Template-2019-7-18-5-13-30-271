package com.thoughtworks.parking_lot.model;

import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class ParkingLotTest {

  @Autowired
  private ParkingLotRepository repository;

  @Test
  public void shoule_save_parking_lot(){
    ParkingLot parkingLot = expectParkingLotByName("OOCL");

    ParkingLot saveParkingLot = repository.save(parkingLot);

    assertThat(saveParkingLot.getParkingLotName()).isEqualTo(parkingLot.getParkingLotName());
  }

  @Test
  public void shoule_find_all_parking_lots_with_pagination(){
    repository.save(expectParkingLotByName("OOCL"));
    repository.save(expectParkingLotByName("OOCL1"));
    repository.save(expectParkingLotByName("OOCL2"));
    Pageable pageable = PageRequest.of(1, 2);

    Page<ParkingLot> lots = repository.findAllParkingLotsWithPagination(pageable);

    assertThat(lots.getSize()).isEqualTo(2);
  }

  private ParkingLot expectParkingLotByName(String name) {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setParkingLotName(name);
    parkingLot.setCapacity(10);
    parkingLot.setLocation("珠海");
    return parkingLot;
  }

}