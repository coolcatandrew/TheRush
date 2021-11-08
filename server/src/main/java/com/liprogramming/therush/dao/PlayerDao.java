package com.liprogramming.therush.dao;

import com.liprogramming.therush.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDao extends JpaRepository<Player, Long> {
    Player getById(Long id);

    List<Player> getByIdIsInOrderById(List<Long> playerIds);

}
