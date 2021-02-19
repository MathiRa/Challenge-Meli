package com.meli.challenge.controller;

import com.meli.challenge.model.Dna;
import com.meli.challenge.model.StatsResponse;
import com.meli.challenge.service.MyService;
import java.util.Arrays;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final MyService service;

  public Controller(MyService service) {
    this.service = service;
  }

  @PostMapping(value = "/mutant", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> isMutant(@RequestBody Dna dna) {
    var isValid = checkDnaConsistency(dna);
    if (!isValid) {
      return ResponseEntity.badRequest().build();
    }
    var isMutant = service.isMutant(dna);
    return isMutant ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.FORBIDDEN).build();
  }

  private boolean checkDnaConsistency(Dna dna) {
    var dnaData = dna.getDnaValue();
    if (dnaData.length > 0) {
      var length = dnaData.length;
      var pattern = Pattern.compile("^[ATCG]{" + length + "}$");
      return Arrays.stream(dnaData).allMatch(line -> checkLine(line, length, pattern));
    } else {
      return false;
    }
  }

  private boolean checkLine(String line, int length, Pattern pattern) {
    return line != null && line.length() == length && pattern.matcher(line).matches();
  }

  @GetMapping(value = "/stats", produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<StatsResponse> getStats() {
    return ResponseEntity.ok(service.getStats());
  }

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler({IllegalArgumentException.class, HttpMessageNotReadableException.class})
  public void handleException() {
    //Empty body
  }

}
