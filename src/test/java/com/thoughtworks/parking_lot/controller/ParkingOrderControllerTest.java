package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingOrderService;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingOrderController.class)
@ExtendWith(SpringExtension.class)
class ParkingOrderControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private ParkingOrderService orderService;

  @Test
  void should_add_parking_order() throws Exception {
    String carNumber = "粤A12345";
    String parkingLotName = "OOCL";
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setCapacity(10);
    parkingLot.setParkingOrders(new ArrayList<>());
    when(orderService.createParkingOrder(anyString(),any())).thenReturn("");

    ResultActions result = mvc.perform(post("/parking-orders" +
        "?parkingLotName={parkingLotName}&carNumber={carNumber}", parkingLotName, carNumber));

    result.andExpect(status().isOk()).andExpect(content().string(""));
  }

  @Test
  void should_not_add_parking_order_when_parking_lot_is_full() throws Exception {
    String carNumber = "粤A12345";
    String parkingLotName = "OOCL";
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setCapacity(0);
    parkingLot.setParkingOrders(new ArrayList<>());

    ResultActions result = mvc.perform(post("/parking-orders" +
        "?parkingLotName={parkingLotName}&carNumber={carNumber}", parkingLotName, carNumber));

    result.andExpect(status().isOk());
  }

  @Test
  void should_update_parking_order() throws Exception {
    String carNumber = "粤A12345";

    ResultActions result = mvc.perform(put("/parking-orders" +
        "?carNumber={carNumber}", carNumber));

    result.andExpect(status().isOk());
    verify(orderService).fetchCar(any());
  }


}