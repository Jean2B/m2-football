package com.m2dfs.StatsService.controller;

import com.m2dfs.StatsService.model.Player;
import com.m2dfs.StatsService.model.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class StatsController {
    @Autowired
    RestTemplate restTemplate;

    @Operation(summary = "Get team and players info by team id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Team not found") })
    @GetMapping(value = "/team-stats/{id}")
    public String getTeamStats(@PathVariable(value = "id") int id) {
        Team team = getTeamInfo(id);
        String response = "ID : " + team.getId() +
                "<br>Nom : " + team.getName() +
                "<br><br>### Players ###<br>";
        Player playerInfo;
        for (int playerId : team.getPlayerList()) {
            playerInfo = getPlayerInfo(playerId);
            response += "<br>" + getPlayerInfoStr(playerInfo);
        }
        return response;
    }

    @Operation(summary = "Get player stats by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Player not found") })
    @GetMapping(value = "/player-stats/{id}")
    public String getPlayerStats(@PathVariable(value = "id") int id) {
        Player playerInfo = getPlayerInfo(id);
        return getPlayerInfoStr(playerInfo);
    }

    //Obtention des infos du joueur depuis player-service
    public Player getPlayerInfo(int playerId) {
        System.out.println("Getting Player info for " + playerId);

        Player playerInfo = this.restTemplate.exchange("http://player-service/players/{playerId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Player>() {
                }, playerId).getBody();

        System.out.println("Response Body " + playerInfo);

        return playerInfo;
    }

    //Infos du joueur sous forme de String
    public String getPlayerInfoStr(Player player) {
        return "ID : " + player.getId() +
                "<br>Name : " + player.getName() +
                " (" + player.getNumber() + ")";
    }

    //Obtention des infos de l'équipe depuis team-service
    public Team getTeamInfo(int teamId) {
        System.out.println("Getting Team info for " + teamId);

        Team teamInfo = this.restTemplate.exchange("http://team-service/teams/{teamId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Team>() {
                }, teamId).getBody();

        System.out.println("Response Body " + teamInfo);

        return teamInfo;
    }
}
