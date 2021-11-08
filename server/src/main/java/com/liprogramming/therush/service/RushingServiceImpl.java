package com.liprogramming.therush.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.liprogramming.therush.dao.PlayerDao;
import com.liprogramming.therush.dao.RushingStatisticDao;
import com.liprogramming.therush.data.RushingData;
import com.liprogramming.therush.model.Player;
import com.liprogramming.therush.model.RushingStatistic;
import com.liprogramming.therush.util.RushingCsvWriterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RushingServiceImpl implements RushingService {
  @Autowired private PlayerDao playerDao;
  @Autowired private RushingStatisticDao rushingStatisticDao;

  public void setPlayerDao(PlayerDao playerDao) {
    this.playerDao = playerDao;
  }

  public void setRushingStatisticDao(RushingStatisticDao rushingStatisticDao) {
    this.rushingStatisticDao = rushingStatisticDao;
  }

  @Override
  public List<Player> upload(MultipartFile file) throws IOException, JsonParseException {
    String json = new String(file.getBytes());
    Gson g =
        new GsonBuilder()
            .registerTypeAdapter(
                new TypeToken<List<Player>>() {}.getType(), new Player.PlayerDeserializer())
            .create();

    List<Player> players = g.fromJson(json, new TypeToken<List<Player>>() {}.getType());

    playerDao.saveAll(players);
    List<RushingStatistic> rushingStatistics =
        players.stream()
            .peek(player -> player.getRushingStatistic().setPlayerId(player.getId()))
            .map(Player::getRushingStatistic)
            .collect(Collectors.toList());
    rushingStatisticDao.saveAll(rushingStatistics);
    return players;
  }

  @Override
  public List<Player> getAllPlayers() {
    return playerDao.findAll();
  }

  @Override
  public void addPlayer(Player player) {
    playerDao.save(player);
    RushingStatistic rushingStatistic = player.getRushingStatistic();
    rushingStatistic.setPlayerId(player.getId());
    rushingStatisticDao.save(rushingStatistic);
  }

  @Override
  public void exportCsv(List<Player> players, HttpServletResponse response) throws IOException{
    RushingCsvWriterUtil writerUtil = new RushingCsvWriterUtil(response);
    writerUtil.writeToCSV(players);
  }

  @Override
  public String getDataColumns() {
    return RushingData.toJson();
  }
}
