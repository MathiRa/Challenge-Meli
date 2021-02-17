package com.meli.challenge.model;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("dna")
public class Dna implements Serializable {
  @Id
  private String[] dna;

}
