package com.m2dfs.StatsService.controller;

import com.m2dfs.StatsService.model.Player;
import com.m2dfs.StatsService.model.Team;
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

    @GetMapping(value = "/team-stats/{id}")
    public String getTeamStats(@PathVariable(value = "id") int id) {
        Team team = getTeamInfo(id);
        String response = "ID : " + team.getId() +
                "<br>Nom : " + team.getName() +
                "<br><br>### Joueurs ###<br>";
        Player playerInfo;
        for (int playerId : team.getPlayerList()) {
            playerInfo = getPlayerInfo(playerId);
            response += "<br>" + getPlayerInfoStr(playerInfo);
        }
        return response;
    }

    @GetMapping(value = "/player-stats/{id}")
    public String getPlayerStats(@PathVariable(value = "id") int id) {
        Player playerInfo = getPlayerInfo(id);
        return getPlayerInfoStr(playerInfo);
    }

    public Player getPlayerInfo(int playerId) {
        System.out.println("Getting Player info for " + playerId);

        Player playerInfo = this.restTemplate.exchange("http://player-service/players/{playerId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Player>() {
                }, playerId).getBody();

        System.out.println("Response Body " + playerInfo);

        return playerInfo;
    }

    public String getPlayerInfoStr(Player player) {
        return "ID : " + player.getId() +
                "<br>Name : " + player.getName() +
                " (" + player.getNumber() + ")";
    }

    public Team getTeamInfo(int teamId) {
        System.out.println("Getting Team info for " + teamId);

        Team teamInfo = this.restTemplate.exchange("http://team-service/teams/{teamId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<Team>() {
                }, teamId).getBody();

        System.out.println("Response Body " + teamInfo);

        return teamInfo;
    }
}
