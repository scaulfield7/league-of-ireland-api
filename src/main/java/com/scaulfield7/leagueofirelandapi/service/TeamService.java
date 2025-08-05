package com.scaulfield7.leagueofirelandapi.service;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamService {
    private final List<Integer> teamIds;
    private final List<Team> teams;

    public TeamService() {
        teamIds = new ArrayList<>();
        teams = new ArrayList<>();

        Team team1 = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team team2 = new Team(2, "Bohemians FC", 8, "Dalymount Park", "Alban Hysa", "https://bohemians.ie");
        Team team3 = new Team(3, "Cork City FC", 9, "Turner's Cross", "Frank Kelleher", "https://corkcityfc.ie");
        Team team4 = new Team(4, "DLR Waves FC", 12, "UCD Bowl", "Laura Heffernan", "https://dlrwaves.com");
        Team team5 = new Team(5, "Galway United FC", 7, "Eamonn Deacy Park", "Phil Trill", "https://galwayunitedfc.ie");
        Team team6 = new Team(6, "Peamount United FC", 6, "Greenogue Park", "Emma Donohue", "https://peamountunited.com");
        Team team7 = new Team(7, "Shamrock Rovers FC", 4, "Tallaght Stadium", "Stephanie Zambra", "https://shamrockrovers.ie");
        Team team8 = new Team(8, "Shelbourne FC", 2, "Tolka Park", "Eoin Wearen", "https://shelbournefc.ie");
        Team team9 = new Team(9, "Sligo Rovers FC", 11, "The Showgrounds", "Steve Feeney", "https://sligorovers.com");
        Team team10 = new Team(10, "Treaty United FC", 5, "Markets Field", "Sean Russell", "https://treatyunitedfc.com");
        Team team11 = new Team(11, "Waterford FC", 10, "Bishop's Gate", "Gary Hunt", "https://waterfordfc.ie");
        Team team12 = new Team(12, "Wexford FC", 3, "Ferrycarrig Park", "Sean Byrne", "https://wexfordfc.ie");

        teams.addAll(Arrays.asList(team1, team2, team3, team4, team5, team6, team7, team8, team9, team10, team11, team12));
        teamIds.addAll(Arrays.asList(team1.getId(), team2.getId(), team3.getId(), team4.getId(),
                team5.getId(), team6.getId(), team7.getId(), team8.getId(), team9.getId(),
                team10.getId(), team11.getId(), team12.getId()));
    }

    public Collection<Team> getAllTeams() {
        if (teams.isEmpty()) {
            throw new RuntimeException("No teams found");
        }
        return teams;
    }

    public Collection<Integer> getAllTeamIds() {
        if (teamIds.isEmpty()) {
            throw new RuntimeException("No team IDs found");
        }
        return teamIds;
    }

    public Optional<Team> getTeamByID(Integer id) {
        Optional<Team> optionalTeam = Optional.empty();
        for (Team team : teams) {
            if (team.getId() == id) {
                optionalTeam = Optional.of(team);
                return optionalTeam;
            }
        }
        return optionalTeam;
    }

    public Optional<Team> getTeamByName(String name) {
        Optional<Team> optionalTeam = Optional.empty();
        for (Team team : teams) {
            if (team.getName().equalsIgnoreCase(name)) {
                optionalTeam = Optional.of(team);
                return optionalTeam;
            }
        }
        return optionalTeam;
    }

    public Optional<Team> getTeamByLeagueRanking(int leagueRanking) {
        Optional<Team> optionalTeam = Optional.empty();
        for (Team team : teams) {
            if (team.getLeagueRanking() == leagueRanking) {
                optionalTeam = Optional.of(team);
                return optionalTeam;
            }
        }
        return optionalTeam;
    }

    public Optional<Team> getTeamByHomePitch(String homePitch) {
        Optional<Team> optionalTeam = Optional.empty();
        for (Team team : teams) {
            if (team.getHomePitch().equalsIgnoreCase(homePitch)) {
                optionalTeam = Optional.of(team);
                return optionalTeam;
            }
        }
        return optionalTeam;
    }

    public Optional<Team> getTeamByManager(String manager) {
        Optional<Team> optionalTeam = Optional.empty();
        for (Team team : teams) {
            if (team.getManager().equalsIgnoreCase(manager)) {
                optionalTeam = Optional.of(team);
                return optionalTeam;
            }
        }
        return optionalTeam;
    }
}
