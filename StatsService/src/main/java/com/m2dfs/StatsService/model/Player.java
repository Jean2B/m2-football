package com.m2dfs.StatsService.model;

public class Player {
    private int id;
    private String name;
    private int number; //Numéro maillot

    public Player() {}

    public Player(int id, String name, int number) {
        super();
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
