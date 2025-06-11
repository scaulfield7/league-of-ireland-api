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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeagueRanking() {
        return leagueRanking;
    }

    public void setLeagueRanking(int leagueRanking) {
        this.leagueRanking = leagueRanking;
    }

    public String getHomePitch() {
        return homePitch;
    }

    public void setHomePitch(String homePitch) {
        this.homePitch = homePitch;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
