package com.thoughtworks.parking_lot.model;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table
public class ParkingLot {

  @Id
  @Column(unique = true)
  private String parkingLotName;

  @Min(value = 0)
  private Integer capacity;

  private String location;

  public String getParkingLotName() {
    return parkingLotName;
  }

  public void setParkingLotName(String parkingLotName) {
    this.parkingLotName = parkingLotName;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
