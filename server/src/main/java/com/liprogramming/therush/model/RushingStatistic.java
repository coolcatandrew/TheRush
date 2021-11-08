package com.liprogramming.therush.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class RushingStatistic {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long playerId;
  private Double averageAttempts;
  private Integer attempts;
  private Integer yards;
  private Double averageYards;
  private Double yardsPerGame;
  private Integer touchdowns;
  private String longest;
  private Integer firstDowns;
  private Double firstDownPercentage;
  private Integer twentyPlusYards;
  private Integer fortyPlusYards;
  private Integer fumbles;
  private Date created;

  public RushingStatistic() {
  }

  public RushingStatistic(
          Double averageAttempts,
          Integer attempts,
          Integer yards,
          Double averageYards,
          Double yardsPerGame,
          Integer touchdowns,
          String longest,
          Integer firstDowns,
          Double firstDownPercentage,
          Integer twentyPlusYards,
          Integer fortyPlusYards,
          Integer fumbles) {
    this.averageAttempts = averageAttempts;
    this.attempts = attempts;
    this.yards = yards;
    this.averageYards = averageYards;
    this.yardsPerGame = yardsPerGame;
    this.touchdowns = touchdowns;
    this.longest = longest;
    this.firstDowns = firstDowns;
    this.firstDownPercentage = firstDownPercentage;
    this.twentyPlusYards = twentyPlusYards;
    this.fortyPlusYards = fortyPlusYards;
    this.fumbles = fumbles;
    this.created = new Date();
  }

  public Long getId() {
    return id;
  }

  public Long getPlayerId() {
    return playerId;
  }

  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }

  public Double getAverageAttempts() {
    return averageAttempts;
  }

  public void setAverageAttempts(Double averageAttempts) {
    this.averageAttempts = averageAttempts;
  }

  public Integer getAttempts() {
    return attempts;
  }

  public void setAttempts(Integer attempts) {
    this.attempts = attempts;
  }

  public Integer getYards() {
    return yards;
  }

  public void setYards(Integer yards) {
    this.yards = yards;
  }

  public Double getAverageYards() {
    return averageYards;
  }

  public void setAverageYards(Double averageYards) {
    this.averageYards = averageYards;
  }

  public Double getYardsPerGame() {
    return yardsPerGame;
  }

  public void setYardsPerGame(Double yardsPerGame) {
    this.yardsPerGame = yardsPerGame;
  }

  public Integer getTouchdowns() {
    return touchdowns;
  }

  public void setTouchdowns(Integer touchdowns) {
    this.touchdowns = touchdowns;
  }

  public String getLongest() {
    return longest;
  }

  public void setLongest(String longest) {
    this.longest = longest;
  }

  public Integer getFirstDowns() {
    return firstDowns;
  }

  public void setFirstDowns(Integer firstDowns) {
    this.firstDowns = firstDowns;
  }

  public Double getFirstDownPercentage() {
    return firstDownPercentage;
  }

  public void setFirstDownPercentage(Double firstDownPercentage) {
    this.firstDownPercentage = firstDownPercentage;
  }

  public Integer getTwentyPlusYards() {
    return twentyPlusYards;
  }

  public void setTwentyPlusYards(Integer twentyPlusYards) {
    this.twentyPlusYards = twentyPlusYards;
  }

  public Integer getFortyPlusYards() {
    return fortyPlusYards;
  }

  public void setFortyPlusYards(Integer fortyPlusYards) {
    this.fortyPlusYards = fortyPlusYards;
  }

  public Integer getFumbles() {
    return fumbles;
  }

  public void setFumbles(Integer fumbles) {
    this.fumbles = fumbles;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public String toExportString(String separator) {
    StringBuilder stringBuilder = new StringBuilder();

    stringBuilder
            .append(this.attempts)
            .append(separator)
            .append(this.averageAttempts)
            .append(separator)
            .append(this.yards)
            .append(separator)
            .append(this.averageYards)
            .append(separator)
            .append(this.yardsPerGame)
            .append(separator)
            .append(this.touchdowns)
            .append(separator)
            .append(this.longest)
            .append(separator)
            .append(this.firstDowns)
            .append(separator)
            .append(this.firstDownPercentage)
            .append(separator)
            .append(this.twentyPlusYards)
            .append(separator)
            .append(this.fortyPlusYards)
            .append(separator)
            .append(this.fumbles);

    return stringBuilder.toString();
  }
}
