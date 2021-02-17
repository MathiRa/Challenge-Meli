package com.meli.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.meli.challenge.controller.Controller;
import com.meli.challenge.model.Dna;
import com.meli.challenge.service.MyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;


class ServiceTests {

  private final MyService service = mock(MyService.class);
  private Controller controller;

  private Dna DNA_200;
  private Dna DNA_403;
  private final String[] DNA_200_VALUE= {"AAAA","TTTT"};
  private final String[] DNA_403_VALUE= {"TTTT","TTTT"};

  @BeforeEach
  void setUp() {
    controller = new Controller(service);
    DNA_200 = new Dna();
    DNA_200.setDna(DNA_200_VALUE);
    when(service.isMutant(eq(DNA_200))).thenReturn(true);
    DNA_403 = new Dna();
    DNA_403.setDna(DNA_403_VALUE);
    when(service.isMutant(eq(DNA_403))).thenReturn(false);
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

}
