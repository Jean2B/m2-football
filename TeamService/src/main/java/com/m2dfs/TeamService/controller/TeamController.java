package com.m2dfs.TeamService.controller;

import com.m2dfs.TeamService.model.Team;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {
    List<Team> teams = new ArrayList<>();
    {
        teams.add(new Team(1, "First Team", List.of(1,3)));
    }

    @GetMapping(value = "/teams/{id}")
    public Team getTeam(@PathVariable(value = "id") int id) {
        Team team = teams.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (team == null) {
            return new Team(0, "Not Found", new ArrayList<>());
        }
        return team;
    }

    @PostMapping(value = "/teams")
    public void addTeam(@RequestBody Team newTeam) {
        teams.add(newTeam);
    }

    @PutMapping(value = "/teams/{id}")
    public Team editTeam(@PathVariable(value = "id") int id, @RequestBody Team teamInfo) {
        Team team = teams.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (team == null) {
            return new Team(0, "Not Found", new ArrayList<>());
        }
        team.setName(teamInfo.getName());
        team.setPlayerList(teamInfo.getPlayerList());
        return team;
    }

    @DeleteMapping(value = "/teams/{id}")
    public void deleteTeam(@PathVariable(value = "id") int id) {
        teams.removeIf(team -> team.getId() == id);
    }
}
