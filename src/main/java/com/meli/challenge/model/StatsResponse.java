package com.meli.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;

@Data
public class StatsResponse {
  @JsonProperty(value = "count_mutant_dna")
  private long count_mutant_dna ;
  @JsonProperty(value = "count_human_dna")
  private long count_human_dna ;
  @JsonProperty(value = "ratio")
  private float ratio ;
}
