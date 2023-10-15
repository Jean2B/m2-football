package com.m2dfs.MatchService.model;

import java.util.List;

public class Match {
    private int id;
    private String name;
    private List<Integer> teamList; //ID des équipes du match

    public Match(int id, String name, List<Integer> teamList) {
        this.id = id;
        this.name = name;
        this.teamList = teamList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getTeamList() {
        return teamList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeamList(List<Integer> teamList) {
        this.teamList = teamList;
    }
}
