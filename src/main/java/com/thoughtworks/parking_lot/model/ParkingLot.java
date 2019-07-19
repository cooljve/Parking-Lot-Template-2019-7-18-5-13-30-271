package com.thoughtworks.parking_lot.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
@Table
public class ParkingLot {

  @Id
  @Column(unique = true)
  private String parkingLotName;

  @Min(value = 0)
  private Integer capacity;

  private String location;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "parking_lot_name")
  private List<ParkingOrder> parkingOrders;

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

  public List<ParkingOrder> getParkingOrders() {
    return parkingOrders;
  }

  public void setParkingOrders(List<ParkingOrder> parkingOrders) {
    this.parkingOrders = parkingOrders;
  }
}
