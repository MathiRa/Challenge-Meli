package com.meli.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class StatsResponse {

  @JsonProperty(value = "count_mutant_dna")
  private long countMutant;
  @JsonProperty(value = "count_human_dna")
  private long countHuman;
  @JsonProperty(value = "ratio")
  private float ratio;

}
