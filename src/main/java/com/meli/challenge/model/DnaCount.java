package com.meli.challenge.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "dna_count")
@Data
public class DnaCount implements Serializable {

  @Id
  private Integer id;
  @Column(name = "human_count")
  private Long humanCount;
  @Column(name ="mutant_count")
  private Long mutantCount;

}
