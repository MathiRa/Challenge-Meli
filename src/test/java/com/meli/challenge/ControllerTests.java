package com.meli.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.meli.challenge.controller.Controller;
import com.meli.challenge.model.Dna;
import com.meli.challenge.model.StatsResponse;
import com.meli.challenge.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


class ControllerTests {

  private final MyService service = mock(MyService.class);
  private final String[] DNA_200_VALUE = {"AAAA", "TTTT"};
  private final String[] DNA_403_VALUE = {"TTTT", "TTTT"};
  private Controller controller;
  private Dna DNA_200;
  private Dna DNA_403;

  @BeforeEach
  void setUp() {
    controller = new Controller(service);
    DNA_200 = new Dna();
    DNA_200.setDnaData(DNA_200_VALUE);
    when(service.isMutant(DNA_200)).thenReturn(true);
    DNA_403 = new Dna();
    DNA_403.setDnaData(DNA_403_VALUE);
    when(service.isMutant(DNA_403)).thenReturn(false);
    StatsResponse statsResponse = new StatsResponse();
    when(service.getStats()).thenReturn(statsResponse);
  }

  @Test
  void dna200OKTest() {
    var response = controller.isMutant(DNA_200);
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void dna403OKTest() {
    var response = controller.isMutant(DNA_403);
    assertNotNull(response);
    assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
  }

  @Test
  void statsTest() {
    var response = controller.getStats();
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
