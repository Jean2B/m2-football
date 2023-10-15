package com.m2dfs.PlayerService.controller;

import com.m2dfs.PlayerService.model.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get a player by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Player not found") })
    @GetMapping(value = "/players/{id}")
    public Player getPlayer(@PathVariable(value = "id") int id) {
        Player player = players.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (player == null) {
            return new Player(0, "Not Found", 0);
        }
        return player;
    }

    @Operation(summary = "Add a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden") })
    @PostMapping(value = "/players")
    public void addPlayer(@RequestBody Player newPlayer) {
        players.add(newPlayer);
    }

    @Operation(summary = "Edit an existing player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Player not found") })
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

    @Operation(summary = "Delete a player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Player not found") })
    @DeleteMapping(value = "/players/{id}")
    public void deletePlayer(@PathVariable(value = "id") int id) {
        players.removeIf(player -> player.getId() == id);
    }
}
