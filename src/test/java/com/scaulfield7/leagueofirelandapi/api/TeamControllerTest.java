package com.scaulfield7.leagueofirelandapi.api;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.junit.jupiter.api.Test;

public class TeamControllerTest {
    
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
}
