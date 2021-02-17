package com.meli.challenge.controller;

import com.meli.challenge.model.Dna;
import com.meli.challenge.model.StatsResponse;
import com.meli.challenge.service.MyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final MyService service;

  public Controller(MyService service) {
    this.service = service;
  }

  @PostMapping(value = "/mutant", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<?> isMutant(@RequestBody Dna dna) {
    var isMutant = service.isMutant(dna);
    return isMutant ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  @GetMapping(value = "/stats", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<StatsResponse> getStats() {
    return ResponseEntity.ok(service.getStats());
  }

}
