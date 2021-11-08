package com.liprogramming.therush.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.liprogramming.therush.data.RushingData;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String team;
  private String position;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "id", referencedColumnName = "playerId")
  private RushingStatistic rushingStatistic;

  public Player() {}

  public Player(String name, String team, String position) {
    this(name, team, position, new RushingStatistic());
  }

  public Player(String name, String team, String position, RushingStatistic rushingStatistic) {
    this.name = name;
    this.team = team;
    this.position = position;
    this.rushingStatistic = rushingStatistic;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public RushingStatistic getRushingStatistic() {
    return rushingStatistic;
  }

  public void setRushingStatistic(RushingStatistic rushingStatistic) {
    this.rushingStatistic = rushingStatistic;
  }

  public String toExportString(String separator) {
    StringBuilder stringBuilder = new StringBuilder();
    String name = this.name;
    if (name.contains(",")) {
      String[] split = name.split(",");
      name = "";
      if (split.length > 1) {
        name = " " + split[1];
      }
      name += split[0];
    }

    stringBuilder
            .append(name)
            .append(separator)
            .append(this.team.replaceAll(",", ""))
            .append(separator)
            .append(this.position.replaceAll(",", ""))
            .append(separator)
            .append(rushingStatistic.toExportString(separator));

    return stringBuilder.toString();
  }

  /**
   * Players are only the same if they contain no null values and the same values are the same.
   *
   * @param player
   * @return player objects have the same attributes
   */
  public boolean equals(Player player) {
    return player != null
            && (this.name != null && this.name.equals(player.getName()))
            && (this.team != null && this.team.equals(player.getTeam()))
            && (this.position != null && this.position.equals(player.getPosition()));
  }

  public static class PlayerDeserializer implements JsonDeserializer<List<Player>> {

    @Override
    public List<Player> deserialize(
            JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
      JsonArray jsonList = jsonElement.getAsJsonArray();
      List<Player> players = new ArrayList<>();

      for (JsonElement el : jsonList) {
        JsonObject json = el.getAsJsonObject();
        JsonElement name = json.get(RushingData.PLAYER.getDataName());
        JsonElement team = json.get(RushingData.TEAM.getDataName());
        JsonElement pos = json.get(RushingData.POSITION.getDataName());
        JsonElement attAvg = json.get(RushingData.ATTEMPTS_AVERAGE.getDataName());
        JsonElement att = json.get(RushingData.ATTEMPTS.getDataName());
        JsonElement yrd = json.get(RushingData.YARDS.getDataName());
        JsonElement yrdAvg = json.get(RushingData.YARDS_AVERAGE_PER_ATTEMPT.getDataName());
        JsonElement yrdPerGame = json.get(RushingData.YARDS_PER_GAME.getDataName());
        JsonElement td = json.get(RushingData.TOUCHDOWNS.getDataName());
        JsonElement longest = json.get(RushingData.LONGEST.getDataName());
        JsonElement firstDown = json.get(RushingData.FIRST_DOWN.getDataName());
        JsonElement firstDownPer = json.get(RushingData.FIRST_DOWN_PERCENTAGE.getDataName());
        JsonElement twentyYrd = json.get(RushingData.TWENTY_YARDS.getDataName());
        JsonElement fortyYrd = json.get(RushingData.FORTY_YARDS.getDataName());
        JsonElement fumbles = json.get(RushingData.FUMBLES.getDataName());

        Player player =
                new Player(
                        name == null ? null : name.getAsString(),
                        team == null ? null : team.getAsString(),
                        pos == null ? null : pos.getAsString());

        RushingStatistic rushingStatistic =
                new RushingStatistic(
                        attAvg == null ? null : attAvg.getAsDouble(),
                        att == null ? null : att.getAsInt(),
                        yrd == null ? null : Integer.parseInt(yrd.getAsString().replaceAll(",", "")),
                        yrdAvg == null ? null : yrdAvg.getAsDouble(),
                        yrdPerGame == null ? null : yrdPerGame.getAsDouble(),
                        td == null ? null : td.getAsInt(),
                        longest == null ? null : longest.getAsString(),
                        firstDown == null ? null : firstDown.getAsInt(),
                        firstDownPer == null ? null : firstDownPer.getAsDouble(),
                        twentyYrd == null ? null : twentyYrd.getAsInt(),
                        fortyYrd == null ? null : fortyYrd.getAsInt(),
                        fumbles == null ? null : fumbles.getAsInt());
        player.setRushingStatistic(rushingStatistic);
        players.add(player);
      }

      return players;
    }
  }
}
