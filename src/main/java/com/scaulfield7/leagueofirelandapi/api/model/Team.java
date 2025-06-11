package com.scaulfield7.leagueofirelandapi.api.model;

public class Team {
    private int id;
    private String name;
    private int leagueRanking;
    private String homePitch;
    private String manager;
    private String website;

    public Team(int id, String name, int leagueRanking, String homePitch, String manager, String website) {
        this.id = id;
        this.name = name;
        this.leagueRanking = leagueRanking;
        this.homePitch = homePitch;
        this.manager = manager;
        this.website = website;
    }
}
