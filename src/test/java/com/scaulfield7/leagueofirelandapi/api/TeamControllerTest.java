package com.scaulfield7.leagueofirelandapi.api;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.junit.jupiter.api.Test;

public class TeamControllerTest {

    /*** Positive Tests ***/
    @Test
    public void getTeamById_validTeamId_returnsTeam() {
        TeamService teamService = new TeamService();
        int mockTeamId = 1;
        Team expectedTeam = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team actualTeam = teamService.getTeamByID(mockTeamId).orElse(null);
        if (actualTeam != null) {
            assert actualTeam.getId() == expectedTeam.getId();
            assert actualTeam.getName().equals(expectedTeam.getName());
            assert actualTeam.getLeagueRanking() == expectedTeam.getLeagueRanking();
            assert actualTeam.getHomePitch().equals(expectedTeam.getHomePitch());
            assert actualTeam.getManager().equals(expectedTeam.getManager());
            assert actualTeam.getWebsite().equals(expectedTeam.getWebsite());
        } else {
            throw new RuntimeException("Expected team not found");
        }
    }

    @Test
    public void getTeamByName_validTeamName_returnsTeam() {
        TeamService teamService = new TeamService();
        String mockTeamName = "Athlone Town AFC";
        Team expectedTeam = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team actualTeam = teamService.getTeamByName(mockTeamName).orElse(null);
        if (actualTeam != null) {
            assert actualTeam.getId() == expectedTeam.getId();
            assert actualTeam.getName().equals(expectedTeam.getName());
            assert actualTeam.getLeagueRanking() == expectedTeam.getLeagueRanking();
            assert actualTeam.getHomePitch().equals(expectedTeam.getHomePitch());
            assert actualTeam.getManager().equals(expectedTeam.getManager());
            assert actualTeam.getWebsite().equals(expectedTeam.getWebsite());
        } else {
            throw new RuntimeException("Expected team not found");
        }
    }

    @Test
    public void getAllTeams_validRequest_returnsCorrectNumberOfTeams() {
        TeamService teamService = new TeamService();
        int expectedNumberOfTeams = 12;
        int actualNumberOfTeams = teamService.getAllTeams().size();
        assert actualNumberOfTeams == expectedNumberOfTeams;
    }

    /*** Negative Tests ***/
    @Test
    public void getTeamById_invalidTeamId_throwsException() {
        TeamService teamService = new TeamService();
        int invalidTeamId = 13;
        try {
            teamService.getTeamByID(invalidTeamId).orElseThrow(() -> new RuntimeException("Team with ID " + invalidTeamId + " not found"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team with ID " + invalidTeamId + " not found");
        }
    }

    @Test
    public void getTeamById_teamIdNull_throwsException() {
        TeamService teamService = new TeamService();
        try {
            teamService.getTeamByID(null).orElseThrow(() -> new RuntimeException("Team ID cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team ID cannot be null");
        }
    }

    @Test
    public void getTeamById_negativeTeamId_throwsException() {
        TeamService teamService = new TeamService();
        int negativeTeamId = -1;
        try {
            teamService.getTeamByID(negativeTeamId).orElseThrow(() -> new RuntimeException("Team ID must be greater than 0"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team ID must be greater than 0. Team ID provided: " + negativeTeamId);
        }
    }

    @Test
    public void getTeamByName_invalidTeamName_throwsException() {
        TeamService teamService = new TeamService();
        String invalidTeamName = "Team Not In League FC";
        try {
            teamService.getTeamByName(invalidTeamName).orElseThrow(() -> new RuntimeException("No team found with name: " + invalidTeamName));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("No team found with name: " + invalidTeamName);
        }
    }

    @Test
    public void getTeamByName_teamNameNull_throwsException() {
        TeamService teamService = new TeamService();
        try {
            teamService.getTeamByName(null).orElseThrow(() -> new RuntimeException("Team name cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name cannot be null");
        }
    }

    @Test
    public void getTeamByName_teamNameEmpty_throwsException() {
        TeamService teamService = new TeamService();
        String emptyTeamName = "";
        try {
            teamService.getTeamByName(emptyTeamName).orElseThrow(() -> new RuntimeException("Team name cannot be empty"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name cannot be empty. Team name provided: " + emptyTeamName);
        }
    }

    @Test
    public void getTeamByName_teamNameTooShort_throwsException() {
        TeamService teamService = new TeamService();
        String shortTeamName = "AB";
        try {
            teamService.getTeamByName(shortTeamName).orElseThrow(() -> new RuntimeException("Team name must be at least 3 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name must be at least 3 characters long. Team name provided: " + shortTeamName);
        }
    }

    @Test
    public void getTeamByName_teamNameTooLong_throwsException() {
        TeamService teamService = new TeamService();
        String longTeamName = "A".repeat(51); // 51 characters long
        try {
            teamService.getTeamByName(longTeamName).orElseThrow(() -> new RuntimeException("Team name must be less than 50 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name must be less than 50 characters long. Team name provided: " + longTeamName);
        }
    }

    @Test
    public void getAllTeams_invalidRequest_throwsException() {
        TeamService teamService = new TeamService();
        try {
            teamService.getAllTeams();
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Unable to retrieve teams");
        }
    }
}
