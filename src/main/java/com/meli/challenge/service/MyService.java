package com.meli.challenge.service;

import com.meli.challenge.model.Dna;
import com.meli.challenge.model.DnaCount;
import com.meli.challenge.model.StatsResponse;
import com.meli.challenge.repository.MyRepository;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MyService {

  private final MyRepository repository;

  public MyService(MyRepository repository) {
    this.repository = repository;
  }

  @Cacheable(cacheNames = "dna", key = "#dna.dna")
  public boolean isMutant(Dna dna) {
    int hits = 0;

    Random random = new Random();
    var mutantCheck = random.nextBoolean();
    if (dna.getDna()[0].length() < 4) {
      return false;
    }
    updateCount(mutantCheck);
    return mutantCheck;
  }

  private int horizontalCheck(String[] lines) {
    int count = 0;
    for (int i = 0; i < lines.length && count < 1; i++) {
      int length = lines[i].length();
      char actual = lines[i].charAt(0);
      int streakCount = 0;
      char[] charArray = lines[i].toCharArray();
      for (int j = 1, charArrayLength = charArray.length; streakCount <4 && j < charArrayLength; j++) {
        char letter = charArray[j];
        if (actual == letter) {
          streakCount++;
        }
      }
      String line = lines[i];
      line.equals("");
    }
    return count;
  }

  @Transactional
  void updateCount(boolean isMutant) {
    var queryCount = repository.findById(1);
    if (queryCount.isPresent()) {
      var dnaCount = queryCount.get();
      long count;
      if (isMutant) {
        count = dnaCount.getMutantCount() + 1;
        dnaCount.setMutantCount(count);
      } else {
        count = dnaCount.getHumanCount() + 1;
        dnaCount.setHumanCount(count);
      }
      repository.save(dnaCount);
    }

  }

  public StatsResponse getStats() {
    var queryCount = repository.findById(1);
    var statsResponse = new StatsResponse();
    if (queryCount.isPresent()) {
      var dnaCount = queryCount.get();
      statsResponse = buildStatsResponse(dnaCount);
    }
    return statsResponse;
  }

  private StatsResponse buildStatsResponse(DnaCount dnaCount) {
    var statsResponse = new StatsResponse();
    var mutants = dnaCount.getMutantCount();
    var humans = dnaCount.getHumanCount();
    statsResponse.setCount_human_dna(humans);
    statsResponse.setCount_mutant_dna(mutants);
    if (humans != 0 && mutants != 0) {
      statsResponse.setRatio((float) mutants / humans);
    }
    return statsResponse;
  }
}
