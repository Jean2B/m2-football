package com.m2dfs.PlayerService.controller;

import com.m2dfs.PlayerService.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {
    List<Player> players = new ArrayList<>();
    {
        players.add(new Player(1, "Marco", 24));
        players.add(new Player(2, "Louis", 15));
        players.add(new Player(3, "Lionel", 10));
    }

    @GetMapping(value = "/players/{id}")
    public Player getPlayer(@PathVariable(value = "id") int id) {
        Player player = players.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (player == null) {
            return new Player(0, "Not Found", 0);
        }
        return player;
    }

    @PostMapping(value = "/players")
    public void addPlayer(@RequestBody Player newPlayer) {
        players.add(newPlayer);
    }

    @PutMapping(value = "/players/{id}")
    public Player editPlayer(@PathVariable(value = "id") int id, @RequestBody Player playerInfo) {
        Player player = players.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (player == null) {
            return new Player(0, "Not Found", 0);
        }
        player.setName(playerInfo.getName());
        player.setNumber(playerInfo.getNumber());
        return player;
    }

    @DeleteMapping(value = "/players/{id}")
    public void deletePlayer(@PathVariable(value = "id") int id) {
        players.removeIf(player -> player.getId() == id);
    }
}
