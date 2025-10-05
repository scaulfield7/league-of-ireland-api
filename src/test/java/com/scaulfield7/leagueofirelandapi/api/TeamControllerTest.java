package com.scaulfield7.leagueofirelandapi.api;

import org.htmlunit.WebClient;
import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.service.ScraperService;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamControllerTest {

    private TeamService teamService;

    @BeforeEach
    public void setUp() throws Exception {
        WebClient webClient = new WebClient();
        ScraperService scraperService = new ScraperService(webClient);
        teamService = new TeamService(scraperService);
    }

    /*** Positive Tests ***/
    @Test
    public void getTeamById_validTeamId_returnsTeam() {
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
    public void getTeamByLeagueRanking_validLeagueRanking_returnsTeam() {
        int mockLeagueRanking = 1;
        Team expectedTeam = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team actualTeam = teamService.getTeamByLeagueRanking(mockLeagueRanking).orElse(null);
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
    public void getTeamByHomePitch_validHomePitch_returnsTeam() {
        String mockHomePitch = "Athlone Town Stadium";
        Team expectedTeam = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team actualTeam = teamService.getTeamByHomePitch(mockHomePitch).orElse(null);
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
    public void getTeamByManager_validManagerName_returnsTeam() {
        String mockManagerName = "Colin Fortune";
        Team expectedTeam = new Team(1, "Athlone Town AFC", 1, "Athlone Town Stadium", "Colin Fortune", "https://athlonetownafc.ie");
        Team actualTeam = teamService.getTeamByManager(mockManagerName).orElse(null);
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
        int expectedNumberOfTeams = 12;
        int actualNumberOfTeams = teamService.getAllTeams().size();
        assert actualNumberOfTeams == expectedNumberOfTeams;
    }

    /*** Negative Tests ***/
    @Test
    public void getTeamById_invalidTeamId_throwsException() {
        int invalidTeamId = 13;
        try {
            teamService.getTeamByID(invalidTeamId).orElseThrow(() -> new RuntimeException("Team with ID " + invalidTeamId + " not found"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team with ID " + invalidTeamId + " not found");
        }
    }

    @Test
    public void getTeamById_teamIdNull_throwsException() {
        try {
            teamService.getTeamByID(null).orElseThrow(() -> new RuntimeException("Team ID cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team ID cannot be null");
        }
    }

    @Test
    public void getTeamById_negativeTeamId_throwsException() {
        int negativeTeamId = -1;
        try {
            teamService.getTeamByID(negativeTeamId).orElseThrow(() -> new RuntimeException("Team ID must be greater than 0"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team ID must be greater than 0. Team ID provided: " + negativeTeamId);
        }
    }

    @Test
    public void getTeamByName_invalidTeamName_throwsException() {
        String invalidTeamName = "Team Not In League FC";
        try {
            teamService.getTeamByName(invalidTeamName).orElseThrow(() -> new RuntimeException("No team found with name: " + invalidTeamName));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("No team found with name: " + invalidTeamName);
        }
    }

    @Test
    public void getTeamByName_teamNameNull_throwsException() {
        try {
            teamService.getTeamByName(null).orElseThrow(() -> new RuntimeException("Team name cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name cannot be null");
        }
    }

    @Test
    public void getTeamByName_teamNameEmpty_throwsException() {
        String emptyTeamName = "";
        try {
            teamService.getTeamByName(emptyTeamName).orElseThrow(() -> new RuntimeException("Team name cannot be empty"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name cannot be empty. Team name provided: " + emptyTeamName);
        }
    }

    @Test
    public void getTeamByName_teamNameTooShort_throwsException() {
        String shortTeamName = "AB";
        try {
            teamService.getTeamByName(shortTeamName).orElseThrow(() -> new RuntimeException("Team name must be at least 3 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name must be at least 3 characters long. Team name provided: " + shortTeamName);
        }
    }

    @Test
    public void getTeamByName_teamNameTooLong_throwsException() {
        String longTeamName = "A".repeat(51); // 51 characters long
        try {
            teamService.getTeamByName(longTeamName).orElseThrow(() -> new RuntimeException("Team name must be less than 50 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team name must be less than 50 characters long. Team name provided: " + longTeamName);
        }
    }

    @Test
    public void getTeamByLeagueRanking_invalidLeagueRanking_throwsException() {
        int invalidLeagueRanking = 13;
        try {
            teamService.getTeamByLeagueRanking(invalidLeagueRanking).orElseThrow(() -> new RuntimeException("Team with league ranking " + invalidLeagueRanking + " not found"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Team with league ranking " + invalidLeagueRanking + " not found");
        }
    }

    @Test
    public void getTeamByLeagueRanking_negativeLeagueRanking_throwsException() {
        int negativeLeagueRanking = -1;
        try {
            teamService.getTeamByLeagueRanking(negativeLeagueRanking).orElseThrow(() -> new RuntimeException("League ranking value must be greater than 0"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("League ranking value must be greater than 0. League ranking value provided: " + negativeLeagueRanking);
        }
    }
    
    @Test
    public void getTeamByHomePitch_invalidHomePitch_throwsException() {
        String invalidHomePitch = "Non-Existent Stadium";
        try {
            teamService.getTeamByHomePitch(invalidHomePitch).orElseThrow(() -> new RuntimeException("No team found with home pitch: " + invalidHomePitch));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("No team found with home pitch: " + invalidHomePitch);
        }
    }

    @Test
    public void getTeamByHomePitch_homePitchNull_throwsException() {
        try {
            teamService.getTeamByHomePitch(null).orElseThrow(() -> new RuntimeException("Home pitch cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Home pitch cannot be null");
        }
    }

    @Test
    public void getTeamByHomePitch_homePitchEmpty_throwsException() {
        String emptyHomePitch = "";
        try {
            teamService.getTeamByHomePitch(emptyHomePitch).orElseThrow(() -> new RuntimeException("Home pitch cannot be empty"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Home pitch cannot be empty. Home pitch provided: " + emptyHomePitch);
        }
    }

    @Test
    public void getTeamByHomePitch_homePitchTooShort_throwsException() {
        String shortHomePitch = "AB";
        try {
            teamService.getTeamByHomePitch(shortHomePitch).orElseThrow(() -> new RuntimeException("Home pitch must be at least 3 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Home pitch must be at least 3 characters long. Home pitch provided: " + shortHomePitch);
        }
    }

    @Test
    public void getTeamByHomePitch_homePitchTooLong_throwsException() {
        String longHomePitch = "A".repeat(51); // 51 characters long
        try {
            teamService.getTeamByHomePitch(longHomePitch).orElseThrow(() -> new RuntimeException("Home pitch must be less than 50 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Home pitch must be less than 50 characters long. Home pitch provided: " + longHomePitch);
        }
    }

    @Test
    public void getTeamByManager_invalidManagerName_throwsException() {
        String invalidManagerName = "Non-Existent Manager";
        try {
            teamService.getTeamByManager(invalidManagerName).orElseThrow(() -> new RuntimeException("No team found with manager: " + invalidManagerName));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("No team found with manager: " + invalidManagerName);
        }
    }

    @Test
    public void getTeamByManager_managerNameNull_throwsException() {
        try {
            teamService.getTeamByManager(null).orElseThrow(() -> new RuntimeException("Manager name cannot be null"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Manager name cannot be null");
        }
    }

    @Test
    public void getTeamByManager_managerNameEmpty_throwsException() {
        String emptyManagerName = "";
        try {
            teamService.getTeamByManager(emptyManagerName).orElseThrow(() -> new RuntimeException("Manager name cannot be empty"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Manager name cannot be empty. Manager name provided: " + emptyManagerName);
        }
    }

    @Test
    public void getTeamByManager_managerNameTooShort_throwsException() {
        String shortManagerName = "AB";
        try {
            teamService.getTeamByManager(shortManagerName).orElseThrow(() -> new RuntimeException("Manager name must be at least 3 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Manager name must be at least 3 characters long. Manager name provided: " + shortManagerName);
        }
    }

    @Test
    public void getTeamByManager_managerNameTooLong_throwsException() {
        String longManagerName = "A".repeat(51); // 51 characters long
        try {
            teamService.getTeamByManager(longManagerName).orElseThrow(() -> new RuntimeException("Manager name must be less than 50 characters long"));
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Manager name must be less than 50 characters long. Manager name provided: " + longManagerName);
        }
    }

    @Test
    public void getAllTeams_invalidRequest_throwsException() {
        try {
            teamService.getAllTeams();
        } catch (RuntimeException e) {
            assert e.getMessage().equals("Unable to retrieve teams");
        }
    }
}
