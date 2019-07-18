package com.thoughtworks.parking_lot.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.parking_lot.model.ParkingLot;
import com.thoughtworks.parking_lot.repository.ParkingLotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParkingLotController.class)
class ParkingLotControllerTest {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private ParkingLotRepository repository;

  @Test
  void should_add_parking_lot() throws Exception {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setParkingLotName("A");
    parkingLot.setLocation("zhuhai");
    parkingLot.setCapacity(10);

    ObjectMapper mapper = new ObjectMapper();
    String parkingLotJson = mapper.writeValueAsString(parkingLot);

    ResultActions result = mvc.perform(post("/parking-lots")
        .contentType(MediaType.APPLICATION_JSON)
        .content(parkingLotJson));

    result.andExpect(status().isOk());

    verify(repository).save(any());
  }

  @Test
  void should_delete_parking_lot() throws Exception {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setParkingLotName("A");
    parkingLot.setLocation("zhuhai");
    parkingLot.setCapacity(10);

    ResultActions result = mvc.perform(delete("/parking-lots/{parkingLotName}",parkingLot.getParkingLotName()));

    result.andExpect(status().isOk());

    verify(repository).deleteById(parkingLot.getParkingLotName());
  }

  @Test
  void should_find_all_parking_lots() throws Exception {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setParkingLotName("A");
    parkingLot.setLocation("zhuhai");
    parkingLot.setCapacity(10);

    ResultActions result = mvc.perform(get("/parking-lots?pageNum={pageNum}",1));

    result.andExpect(status().isOk());

    verify(repository).findAllParkingLotsWithPagination(any());
  }

  @Test
  void should_find_parking_lot_by_name() throws Exception {
    ParkingLot parkingLot = new ParkingLot();
    parkingLot.setParkingLotName("A");
    parkingLot.setLocation("zhuhai");
    parkingLot.setCapacity(10);

    ResultActions result = mvc.perform(get("/parking-lots/{parkingLotName}",parkingLot.getParkingLotName()));

    result.andExpect(status().isOk());

    verify(repository).findById(parkingLot.getParkingLotName());
  }

}