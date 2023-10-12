package com.m2dfs.MatchService.model;

public class Match {
    private int id;
    private String name;

    public Match(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }
}
