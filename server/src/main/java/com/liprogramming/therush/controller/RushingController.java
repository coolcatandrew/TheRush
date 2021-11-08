package com.liprogramming.therush.controller;

import com.google.gson.JsonParseException;
import com.liprogramming.therush.model.Player;
import com.liprogramming.therush.service.RushingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/rushing")
public class RushingController {
  @Autowired RushingService rushingService;

  public RushingService getRushingService() {
    return rushingService;
  }

  @GetMapping(value = "/")
  ResponseEntity<List<Player>> getRushingData() {
    List<Player> players = rushingService.getAllPlayers();
    return ResponseEntity.ok(players);
  }

  @GetMapping(value = "/getDataColumns")
  ResponseEntity<String> getRushingDataColumns() {
    return ResponseEntity.ok(rushingService.getDataColumns());
  }

  @PostMapping(value = "/add")
  public ResponseEntity<Player> add(@RequestBody Player player) {
    try {
      rushingService.addPlayer(player);
      return ResponseEntity.ok(player);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(null);
    }
  }

  @PostMapping(value = "/export")
  public ResponseEntity<String> export(
          @RequestBody List<Player> players, HttpServletResponse response) {
    try {
      rushingService.exportCsv(players, response);
      return ResponseEntity.ok("");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body("Could not export csv. " + e.getMessage());
    }
  }

  @PostMapping(
          value = "/upload",
          consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<List<Player>> upload(@RequestPart("file") MultipartFile file) {
    try {
      return ResponseEntity.ok(rushingService.upload(file));
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.status(500).body(null);
    }
  }
}
