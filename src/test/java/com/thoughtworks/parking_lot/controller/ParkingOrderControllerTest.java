package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.model.ParkingOrder;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import com.thoughtworks.parking_lot.repository.ParkingOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingOrderController.class)
@ExtendWith(SpringExtension.class)
class ParkingOrderControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ParkingOrderRepository orderRepository;

  @MockBean
  private ParkingLotRepository lotRepository;

  @Test
  void should_add_parking_order() throws Exception {
    String carNumber = "粤A12345";
    String parkingLotName = "OOCL";
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setCapacity(10);
    parkingLot.setParkingOrders(new ArrayList<>());
    when(lotRepository.findById(any())).thenReturn(java.util.Optional.ofNullable(parkingLot));

    ResultActions result = mvc.perform(post("/parking-orders" +
        "?parkingLotName={parkingLotName}&carNumber={carNumber}", parkingLotName, carNumber));

    result.andExpect(status().isOk());
    verify(orderRepository).save(any());
  }

  @Test
  void should_update_parking_order() throws Exception {
    String carNumber = "粤A12345";
    ParkingOrder order = new ParkingOrder();
    when(orderRepository.findByCarNumberAndStatusTrue(any())).thenReturn(order);

    ResultActions result = mvc.perform(put("/parking-orders" +
        "?carNumber={carNumber}", carNumber));

    result.andExpect(status().isOk());
    verify(orderRepository).save(any());
  }


}