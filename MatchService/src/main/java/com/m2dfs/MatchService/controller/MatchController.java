package com.m2dfs.MatchService.controller;

import com.m2dfs.MatchService.model.Match;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MatchController {
    List<Match> matches = new ArrayList<>();
    {
        matches.add(new Match(1, "Test Match", new ArrayList<>()));
    }

    @Operation(summary = "Get a match by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Match not found") })
    @CircuitBreaker(name = "getMatch Breaker", fallbackMethod = "getMatch_fallback")
    @GetMapping(value = "/matches/{id}")
    public Match getMatch(@PathVariable(value = "id") int id) {
        Match match = matches.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        if (match == null) {
            return new Match(0, "Not Found", new ArrayList<>());
        }
        return match;
    }

    public Match getMatch_fallback(int id) {
        return new Match(0, "Service unavailable", new ArrayList<>());
    }

    @Operation(summary = "Add a new match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden") })
    @PostMapping(value = "/matches")
    public void addTeam(@RequestBody Match newMatch) {
        matches.add(newMatch);
    }

    @Operation(summary = "Edit an existing match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Match not found") })
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

    @Operation(summary = "Delete a match")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Match not found") })
    @DeleteMapping(value = "/matches/{id}")
    public void deleteMatches(@PathVariable(value = "id") int id) {
        matches.removeIf(team -> team.getId() == id);
    }
}
