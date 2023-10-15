package com.m2dfs.MatchService.controller;

import com.m2dfs.MatchService.model.Match;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchController {
    List<Match> matches = new ArrayList<>();
    {
        matches.add(new Match(1, "Test Match", new ArrayList<>()));
    }

    @GetMapping(value = "/matches/{id}")
    public Match getMatch(@PathVariable(value = "id") int id) {
        Match match = matches.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (match == null) {
            return new Match(0, "Not Found", new ArrayList<>());
        }
        return match;
    }

    @PostMapping(value = "/matches")
    public void addTeam(@RequestBody Match newMatch) {
        matches.add(newMatch);
    }

    @PutMapping(value = "/matches/{id}")
    public Match editMatches(@PathVariable(value = "id") int id, @RequestBody Match matchInfo) {
        Match match = matches.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (match == null) {
            return new Match(0, "Not Found", new ArrayList<>());
        }
        match.setName(matchInfo.getName());
        match.setTeamList(matchInfo.getTeamList());
        return match;
    }

    @DeleteMapping(value = "/matches/{id}")
    public void deleteMatches(@PathVariable(value = "id") int id) {
        matches.removeIf(team -> team.getId() == id);
    }
}
