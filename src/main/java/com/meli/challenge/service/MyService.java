package com.meli.challenge.service;

import com.meli.challenge.model.Dna;
import com.meli.challenge.model.DnaCount;
import com.meli.challenge.model.StatsResponse;
import com.meli.challenge.repository.MyRepository;
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
    var length = dna.getDnaData()[0].length();
    var isMutant = false;
    if (length >= 4) {
      int hits = 0;
      hits += horizontalCheck(dna.getDnaData());
      isMutant = hits >= 2;
      if (!isMutant) {
        hits += verticalCheck(dna.getDnaData());
        isMutant = hits >= 2;
      }
      if (!isMutant) {
        hits += diagonalCheck(dna.getDnaData());
        isMutant = hits >= 2;
      }
    }
    updateCount(isMutant);
    return isMutant;
  }

  private int horizontalCheck(String[] lines) {
    int count = 0;
    for (int i = 0; i < lines.length && count < 2; i++) {
      String line = lines[i];
      char actual = line.charAt(0);
      int streakCount = 0;
      char[] charArray = line.toCharArray();
      for (int j = 1, charArrayLength = charArray.length; j < charArrayLength; j++) {
        char letter = charArray[j];
        if (actual == letter) {
          streakCount++;
          if (streakCount == 3) {
            count++;
          }
        } else {
          streakCount = 0;
          actual = letter;
        }
      }
    }
    return count;
  }

  private int verticalCheck(String[] lines) {
    int count = 0;
    int length = lines.length;
    for (int i = 0; i < length && count < 2; i++) {
      String firstLine = lines[0];
      char actual = firstLine.charAt(i);
      int streakCount = 0;
      for (int j = 1; j < length; j++) {
        char letter = lines[j].charAt(i);
        if (actual == letter) {
          streakCount++;
          if (streakCount == 3) {
            count++;
          }
        } else {
          streakCount = 0;
          actual = letter;
        }
      }
    }
    return count;
  }

  private int diagonalCheck(String[] lines) {
    int count = 0;
    int length = lines.length;
    int iterOutSideDiagonal = length - 4;

    count = diag1(lines, count, length);

    count = diag1Up(lines, count, length, iterOutSideDiagonal);

    count = diag1Down(lines, count, length, iterOutSideDiagonal);

    count = diag2(lines, count, length);

    count = diag2Up(lines, count, length, iterOutSideDiagonal);

    count = diag2Down(lines, count, length, iterOutSideDiagonal);

    return count;
  }

  private int diag1Up(String[] lines, int count, int length, int iterOutSideDiagonal) {
    for (int i = iterOutSideDiagonal; i > 0 && count < 2; i--) {
      String firstLine = lines[0];
      char actual = firstLine.charAt(i);
      int streakCount = 0;
      for (int j = 1; j < length - i; j++) {
        char letter = lines[j].charAt(j + i);
        if (actual == letter) {
          streakCount++;
          if (streakCount == 3) {
            count++;
          }
        } else {
          streakCount = 0;
          actual = letter;
        }
      }
    }
    return count;
  }

  private int diag1Down(String[] lines, int count, int length, int iterOutSideDiagonal) {
    for (int i = iterOutSideDiagonal; i > 0 && count < 2; i--) {
      String firstLine = lines[i];
      char actual = firstLine.charAt(0);
      int streakCount = 0;
      for (int j = 1; j < length - i; j++) {
        char letter = lines[j + i].charAt(j);
        if (actual == letter) {
          streakCount++;
          if (streakCount == 3) {
            count++;
          }
        } else {
          streakCount = 0;
          actual = letter;
        }
      }
    }
    return count;
  }

  private int diag2(String[] lines, int count, int length) {
    String firstLine = lines[0];
    char actual = firstLine.charAt(length - 1);
    int streakCount = 0;
    for (int i = 1; i < length && count < 2; i++) {
      char letter = lines[i].charAt(length - 1 - i);
      if (actual == letter) {
        streakCount++;
        if (streakCount == 3) {
          count++;
        }
      } else {
        streakCount = 0;
        actual = letter;
      }
    }
    return count;
  }

  private int diag2Up(String[] lines, int count, int length, int iterOutSideDiagonal) {
    for (int i = iterOutSideDiagonal; i > 0 && count < 2; i--) {
      String firstLine = lines[0];
      char actual = firstLine.charAt(length - 1 - i);
      int streakCount = 0;
      for (int j = 1; j < length - i && count < 2; j++) {
        char letter = lines[j].charAt(length - i - j - 1);
        if (actual == letter) {
          streakCount++;
          if (streakCount == 3) {
            count++;
          }
        } else {
          streakCount = 0;
          actual = letter;
        }
      }
    }
    return count;
  }

  private int diag2Down(String[] lines, int count, int length, int iterOutSideDiagonal) {
    for (int i = iterOutSideDiagonal; i > 0 && count < 2; i--) {
      String firstLine = lines[i];
      char actual = firstLine.charAt(length - 1);
      int streakCount = 0;
      for (int j = 1; j < length - i && count < 2; j++) {
        char letter = lines[j + i].charAt(length - j - 1);
        if (actual == letter) {
          streakCount++;
          if (streakCount == 3) {
            count++;
          }
        } else {
          streakCount = 0;
          actual = letter;
        }
      }
    }
    return count;
  }

  private int diag1(String[] lines, int count, int length) {
    String firstLine = lines[0];
    char actual = firstLine.charAt(1);
    int streakCount = 0;
    for (int j = 1; j < length && count < 2; j++) {
      char letter = lines[j].charAt(j);
      if (actual == letter) {
        streakCount++;
        if (streakCount == 3) {
          count++;
        }
      } else {
        streakCount = 0;
        actual = letter;
      }
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
    statsResponse.setCountHuman(humans);
    statsResponse.setCountMutant(mutants);
    if (humans != 0 && mutants != 0) {
      statsResponse.setRatio((float) mutants / humans);
    }
    return statsResponse;
  }
}
