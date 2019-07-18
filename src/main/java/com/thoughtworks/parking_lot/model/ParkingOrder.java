package com.thoughtworks.parking_lot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class ParkingOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer orderId;

  private String carNumber;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createTime;

  @Temporal(TemporalType.TIMESTAMP)
  private Date endTime;

  private Boolean status = true;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }


  public String getCarNumber() {
    return carNumber;
  }

  public void setCarNumber(String carNumber) {
    this.carNumber = carNumber;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }
}
