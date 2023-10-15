package com.m2dfs.TeamService.controller;

import com.m2dfs.TeamService.model.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {
    List<Team> teams = new ArrayList<>();
    {
        teams.add(new Team(1, "First Team", List.of(1,3)));
    }

    @Operation(summary = "Get a team by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Team not found") })
    @GetMapping(value = "/teams/{id}")
    public Team getTeam(@PathVariable(value = "id") int id) {
        Team team = teams.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (team == null) {
            return new Team(0, "Not Found", new ArrayList<>());
        }
        return team;
    }

    @Operation(summary = "Add a new team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden") })
    @PostMapping(value = "/teams")
    public void addTeam(@RequestBody Team newTeam) {
        teams.add(newTeam);
    }

    @Operation(summary = "Edit an existing team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Team not found") })
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

    @Operation(summary = "Delete a team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Team not found") })
    @DeleteMapping(value = "/teams/{id}")
    public void deleteTeam(@PathVariable(value = "id") int id) {
        teams.removeIf(team -> team.getId() == id);
    }
}
