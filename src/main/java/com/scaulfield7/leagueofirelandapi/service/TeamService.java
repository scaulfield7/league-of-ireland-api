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

        Team team1 = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team team2 = new Team(2, "Bohemians FC", 8, "Dalymount Park", "Alban Hysa", "https://bohemians.ie");
        Team team3 = new Team(3, "Cork City FC", 9, "Turner's Cross", "Frank Kelleher", "https://corkcityfc.ie");
        Team team4 = new Team(4, "DLR Waves FC", 12, "UCD Bowl", "Laura Heffernan", "https://dlrwaves.com");
        Team team5 = new Team(5, "Galway United FC", 7, "Eamonn Deacy Park", "Phil Trill", "https://galwayunitedfc.ie");
        Team team6 = new Team(6, "Peamount United FC", 6, "", "", "https://peamountunited.com");
        Team team7 = new Team(7, "Shamrock Rovers FC", 4, "Tallaght Stadium", "Collie O'Neill", "https://shamrockrovers.ie");
        Team team8 = new Team(8, "Shelbourne FC", 2, "Tolka Park", "Eoin Wearen", "https://shelbournefc.ie");
        Team team9 = new Team(9, "Sligo Rovers FC", 11, "The Showgrounds", "Steve Feeney", "https://sligorovers.com");
        Team team10 = new Team(10, "Treaty United FC", 5, "Markets Field", "Sean Russell", "https://treatyunitedfc.com");
        Team team11 = new Team(11, "Waterford FC", 10, "Bishop's Gate", "Gary Hunt", "https://waterfordfc.ie");
        Team team12 = new Team(12, "Wexford FC", 3, "Ferrycarrig Park", "Sean Byrne", "https://wexfordfc.ie");
    }

    @GetMapping("/team")
    public Team getTeam(Integer id) {
        for(Team team : teams) {
            if(team.getId() == id) {
                return team;
            }
        }
        return null; // TODO: Handle case where team is not found
    }
}
