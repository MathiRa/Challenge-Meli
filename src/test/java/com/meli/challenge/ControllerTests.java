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
  private final String[] DNA_200_VALUE = {"AAAA", "TTTT", "TTTT", "TTTT"};
  private final String[] DNA_403_VALUE = {"TTTT", "TTTT", "TTTT", "TTTT"};
  private final String[] DNA_400_VALUE1 = {"TTTT"};
  private final String[] DNA_400_VALUE2 = {};
  private final String[] DNA_400_VALUE3 = {"TTTT", "TTTT", "TTTT", "TT1T"};
  private final String[] DNA_400_VALUE4 = {"TTTT", "TTTT", "TTT", "TTTT"};
  private Controller controller;
  private Dna DNA_200;
  private Dna DNA_403;
  private Dna DNA_4001;
  private Dna DNA_4002;
  private Dna DNA_4003;
  private Dna DNA_4004;

  @BeforeEach
  void setUp() {
    controller = new Controller(service);
    DNA_200 = new Dna();
    DNA_200.setDnaValue(DNA_200_VALUE);
    when(service.isMutant(DNA_200)).thenReturn(true);
    DNA_403 = new Dna();
    DNA_403.setDnaValue(DNA_403_VALUE);
    when(service.isMutant(DNA_403)).thenReturn(false);
    StatsResponse statsResponse = new StatsResponse();
    when(service.getStats()).thenReturn(statsResponse);
    DNA_4001 = new Dna();
    DNA_4001.setDnaValue(DNA_400_VALUE1);
    DNA_4002 = new Dna();
    DNA_4002.setDnaValue(DNA_400_VALUE2);
    DNA_4003 = new Dna();
    DNA_4003.setDnaValue(DNA_400_VALUE3);
    DNA_4004 = new Dna();
    DNA_4004.setDnaValue(DNA_400_VALUE4);
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
    controller.handleException();
    var response = controller.getStats();
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  @Test
  void dna4001Test() {
    var response = controller.isMutant(DNA_4001);
    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void dna4002Test() {
    var response = controller.isMutant(DNA_4002);
    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void dna4003Test() {
    var response = controller.isMutant(DNA_4003);
    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  void dna4004Test() {
    var response = controller.isMutant(DNA_4004);
    assertNotNull(response);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

}
