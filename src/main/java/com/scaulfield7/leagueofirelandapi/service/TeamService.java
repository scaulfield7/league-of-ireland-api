package com.scaulfield7.leagueofirelandapi.service;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {
    private List<Team> teams;

    public TeamService() {
        teams = new ArrayList<>();

        Team team1 = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie/");
        Team team2 = new Team(2, "Bohemians FC", 8, "Dalymount Park", "Alban Hysa", "https://bohemians.ie");
        Team team3 = new Team(3, "Cork City FC", 9, "Turner's Cross", "Frank Kelleher", "https://corkcityfc.ie");
        Team team4 = new Team(4, "DLR Waves FC", 12, "UCD Bowl", "Laura Heffernan", "https://dlrwaves.com");
        Team team5 = new Team(5, "Galway United FC", 7, "Eamonn Deacy Park", "Phil Trill", "https://galwayunitedfc.ie");
        Team team6 = new Team(6, "Peamount United FC", 6, "", "", "https://peamountunited.com");
        Team team7 = new Team(7, "Shamrock Rovers FC", 4, "Tallaght Stadium", "Collie O'Neill", "https://shamrockrovers.ie");
    }
}
