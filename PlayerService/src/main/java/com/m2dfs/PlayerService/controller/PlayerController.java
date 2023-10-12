package com.m2dfs.PlayerService.controller;

import com.m2dfs.PlayerService.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlayerController {
    List<Player> players = new ArrayList<>();
    {
        players.add(new Player(1, "Marco"));
    }

    @GetMapping(value = "/players/{id}")
    public Player getPlayer(@PathVariable(value = "id") int id) {
        Player player = players.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (player == null) {
            return new Player(0, "Not Found");
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
            return new Player(0, "Not Found");
        }
        player.setName(playerInfo.getName());
        return player;
    }
    @DeleteMapping(value = "/players/{id}")

    public void deletePlayer(@PathVariable(value = "id") int id) {
        players.removeIf(player -> player.getId() == id);
    }
}
