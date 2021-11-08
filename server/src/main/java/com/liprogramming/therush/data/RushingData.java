package com.liprogramming.therush.data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public enum RushingData {
    PLAYER("Player", "Player's Name", "name"),
    TEAM("Team", "Player's Team Abbreviation", "team"),
    POSITION("Pos", "Player's Position", "position"),
    ATTEMPTS("Att", "Rushing Attempts", "rushingStatistic.attempts"),
    ATTEMPTS_AVERAGE("Att/G", "Rushing Attempts Per Game Average", "rushingStatistic.averageAttempts"),
    YARDS("Yds", "Total Rushing Yards", "rushingStatistic.yards"),
    YARDS_AVERAGE_PER_ATTEMPT("Avg", "Rushing Average Yards Per Attempt", "rushingStatistic.averageYards"),
    YARDS_PER_GAME("Yds/G", "Rushing Yards Per Game", "rushingStatistic.yardsPerGame"),
    TOUCHDOWNS("TD", "Total Rushing Touchdowns", "rushingStatistic.touchdowns"),
    LONGEST("Lng", "Longest Rush (T represents a touchdown occurred)", "rushingStatistic.longest"),
    FIRST_DOWN("1st", "Rushing First Downs", "rushingStatistic.firstDowns"),
    FIRST_DOWN_PERCENTAGE("1st%", "Rushing First Down Percentage", "rushingStatistic.firstDownPercentage"),
    TWENTY_YARDS("20+", "Rushing 20+ Yards Each", "rushingStatistic.twentyPlusYards"),
    FORTY_YARDS("40+", "Rushing 40+ Yards Each", "rushingStatistic.fortyPlusYards"),
    FUMBLES("FUM", "Rushing Fumbles", "rushingStatistic.fumbles");

    private final String dataName;
    private final String header;
    private final String accessor;

    RushingData(String dataName, String header, String accessor) {
        this.dataName = dataName;
        this.header = header;
        this.accessor = accessor;
    }

    public String getDataName() {
        return dataName;
    }

    public String getHeader() {
        return header;
    }

    public String getAccessor() {
        return accessor;
    }

    public static String toJson() {
        JsonArray jsonArray = new JsonArray();
        for (RushingData rushingData : RushingData.values()) {
            JsonObject json = new JsonObject();
            json.addProperty("dataName", rushingData.getDataName());
            json.addProperty("Header", rushingData.getHeader());
            json.addProperty("accessor", rushingData.getAccessor());
            jsonArray.add(json);
        }
        return jsonArray.toString();
    }
}
