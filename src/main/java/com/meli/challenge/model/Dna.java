package com.meli.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


public class Dna implements Serializable {

  @JsonProperty(value = "dna")
  private String[] dnaValue;

  public String[] getDnaValue() {
    return dnaValue;
  }

  public void setDnaValue(String[] dnaValue) {
    this.dnaValue = dnaValue;
  }
}
