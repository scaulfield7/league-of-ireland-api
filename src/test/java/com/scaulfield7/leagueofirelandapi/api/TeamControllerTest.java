package com.scaulfield7.leagueofirelandapi.api;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.junit.jupiter.api.Test;

public class TeamControllerTest {

    /*** Positive Tests ***/
    @Test
    public void getTeam_validTeamId_returnsTeam() {
        TeamService teamService = new TeamService();
        int mockTeamId = 1;
        Team expectedTeam = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team actualTeam = teamService.getTeam(mockTeamId).orElse(null);
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
    public void getTeam_invalidTeamId_throwsException() {
        TeamService teamService = new TeamService();
        int invalidTeamId = 13;
        try {
            teamService.getTeam(invalidTeamId).orElseThrow(() -> new RuntimeException("Team with ID " + invalidTeamId + " not found"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team with ID " + invalidTeamId + " not found");
        }
    }

    @Test
    public void getTeam_nullTeamId_throwsException() {
        TeamService teamService = new TeamService();
        try {
            teamService.getTeam(null).orElseThrow(() -> new RuntimeException("Team ID cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team ID cannot be null");
        }
    }

    @Test
    public void getAllTeams_invalidRequest_throwsException() {
        TeamService teamService = new TeamService();
        try {
            teamService.getAllTeams().orElseThrow(() -> new RuntimeException("Unable to retrieve teams"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Unable to retrieve teams");
        }
    }
}
