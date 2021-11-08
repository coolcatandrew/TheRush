package com.liprogramming.therush.service;

import com.liprogramming.therush.model.Player;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface RushingService {
    /**
     * Upload a json file of rushing data
     * @param file
     * @return
     * @throws IOException
     */
    List<Player> upload(MultipartFile file) throws IOException;

    /**
     * Get all Players
     * @return List<Player>
     */
    List<Player> getAllPlayers();

    /**
     * Add a single player
     * @param player
     */
    void addPlayer(Player player);

    /**
     * Get current data columns from RushingData as json string
     * @return Json
     */
    String getDataColumns();

    /**
     * Exports list of players as a CSV file
     *
     * @param players
     * @throws IOException
     */
    void exportCsv(List<Player> players, HttpServletResponse response) throws IOException;
}
