package com.m2dfs.StatsService.model;

import java.util.List;

public class Team {
    private int id;
    private String name;
    private List<Integer> playerList; //Liste des id des joueurs

    public Team() {}

    public Team(int id, String name, List<Integer> playerList) {
        super();
        this.id = id;
        this.name = name;
        this.playerList = playerList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getPlayerList() {
        return playerList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerList(List<Integer> playerList) {
        this.playerList = playerList;
    }

}
