package com.liprogramming.therush.util;

import com.liprogramming.therush.data.RushingData;
import com.liprogramming.therush.model.Player;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class RushingCsvWriterUtil {
  private static final String COMMA = ",";
  private static final String DOUBLE_QUOTES = "\"";
  private static final String SINGLE_QUOTES = "'";
  private static final String EMBEDDED_DOUBLE_QUOTES = "\"\"";
  private static final String NEW_LINE_UNIX = "\n";
  private static final String NEW_LINE_WINDOWS = "\r\n";

  private HttpServletResponse response;

  public RushingCsvWriterUtil(HttpServletResponse response) {
    this.response = response;
  }

  public void writeToCSV(List<Player> players) throws IOException {
    writeToCSV("TheRush.csv", players);
  }

  public void writeToCSV(String fileName, List<Player> players) throws IOException {
    writeToCSV(fileName, players, true);
  }

  public void writeToCSV(String fileName, List<Player> players, boolean includeHeaders)
      throws IOException {
    StringBuilder sb = new StringBuilder();
    if (includeHeaders) {
      sb.append(getHeader());
      sb.append(System.getProperty("line.separator"));
    }
    for (Player player : players) {
      sb.append(escapeSpecialCharacters(player.toExportString(COMMA)));
      sb.append(System.getProperty("line.separator"));
    }
    response.setContentType("text/csv");
    response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\"");
    byte[] bytes = sb.toString().getBytes();
    response.getOutputStream().write(bytes, 0, bytes.length);
    response.getOutputStream().flush();
  }

  private String getHeader() {
    return getHeader(false);
  }

  private String getHeader(boolean pretty) {
    StringBuilder header = new StringBuilder();
    for (RushingData rushingData : RushingData.values()) {
      header.append((pretty ? rushingData.getHeader() : rushingData.getDataName())).append(COMMA);
    }
    header.setLength(header.length() - 1);
    return header.toString();
  }

  public String escapeSpecialCharacters(String data) {
    return data.replaceAll(NEW_LINE_WINDOWS, " ")
        .replaceAll(NEW_LINE_UNIX, " ")
        .replaceAll("null", "N/A")
        .replaceAll(DOUBLE_QUOTES, EMBEDDED_DOUBLE_QUOTES);
  }
}
