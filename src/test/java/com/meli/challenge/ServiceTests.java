package com.meli.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.meli.challenge.model.Dna;
import com.meli.challenge.model.DnaCount;
import com.meli.challenge.repository.MyRepository;
import com.meli.challenge.service.MyService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ServiceTests {

  private final MyRepository repository = mock(MyRepository.class);
  private MyService service;

  //  private Dna DNA_200;
//  private Dna DNA_403;
//  private final String[] DNA_200_VALUE= {"AAAA","TTTT"};
//  private final String[] DNA_403_VALUE= {"TTTT","TTTT"};
  private DnaCount dnaCount = new DnaCount();

  @BeforeEach
  void setUp() {
    service = new MyService(repository);
    dnaCount.setId(1);
    dnaCount.setMutantCount(0L);
    dnaCount.setHumanCount(0L);
    var queryResponse = Optional.of(dnaCount);
    when(repository.findById(anyInt())).thenReturn(queryResponse);
  }

  @Test
  void getStatsTest() {
    dnaCount.setHumanCount(100L);
    dnaCount.setMutantCount(40L);
    var stats = service.getStats();
    assertNotNull(stats);
    assertEquals(0.4f, stats.getRatio());
  }

  @Test
  void getStatsRatioZeroTest() {
    dnaCount.setHumanCount(0L);
    dnaCount.setMutantCount(0L);
    var stats = service.getStats();
    assertNotNull(stats);
    assertEquals(0.0f, stats.getRatio());
  }

  @Test
  void isMutantFalseLess4Test() {
    var dna = new Dna();
    String[] value = {"AAA", "AAA", "AAA"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertFalse(isMutant);
    assertEquals(1, dnaCount.getHumanCount());
  }

  @Test
  void isMutantFalseTest() {
    var dna = new Dna();
    String[] value = {"ATGC", "CAGT", "TTAT", "AGAC"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertFalse(isMutant);
    assertEquals(1, dnaCount.getHumanCount());
  }

  @Test
  void isMutantTrueVerticalTest() {
    var dna = new Dna();
    String[] value = {"ATGT", "AAGT", "ATAT", "AGAT"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertTrue(isMutant);
    assertEquals(1, dnaCount.getMutantCount());
  }

  @Test
  void isMutantTrueHorizontalTest() {
    var dna = new Dna();
    String[] value = {"AAAA", "CAGT", "TTTT", "AGAC"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertTrue(isMutant);
    assertEquals(1, dnaCount.getMutantCount());
  }

  @Test
  void isMutantTrueDiag1Test() {
    var dna = new Dna();
    String[] value = {"AAAA", "CAGT", "TTAT", "AGAA"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertTrue(isMutant);
    assertEquals(1, dnaCount.getMutantCount());
  }

  @Test
  void isMutantTrueDiag2Test() {
    var dna = new Dna();
    String[] value = {"CTGC", "CACT", "CCAT", "CGAC"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertTrue(isMutant);
    assertEquals(1, dnaCount.getMutantCount());
  }

  @Test
  void isMutantTrueDiag3Test() {
    var dna = new Dna();
    String[] value = {"ATGCG",
                      "CATTG",
                      "TCATT",
                      "AGCCT",
                      "GCGCC"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertTrue(isMutant);
    assertEquals(1, dnaCount.getMutantCount());
  }

  @Test
  void isMutantTrueDiag4Test() {
    var dna = new Dna();
    String[] value = {"ATGGG",
                      "CGGTC",
                      "TGACT",
                      "GGCCC",
                      "GCGCC"};
    dna.setDnaValue(value);
    var isMutant = service.isMutant(dna);
    assertTrue(isMutant);
    assertEquals(1, dnaCount.getMutantCount());
  }
}
