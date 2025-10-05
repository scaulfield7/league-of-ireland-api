package com.scaulfield7.leagueofirelandapi.service;

import org.htmlunit.html.*;
import com.scaulfield7.leagueofirelandapi.api.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamService {
    private final ScraperService scraperService;
    private final List<Team> teams;

    public TeamService(ScraperService scraperService) throws Exception {
        this.scraperService = scraperService;
        this.teams = new ArrayList<>();

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
    }

    /**
     * Initialise teams at application startup
     */
    @PostConstruct
    public void init() {
        try {
            loadTeamsFromWeb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads teams dynamically from the League of Ireland website
     */
    public void loadTeamsFromWeb() throws Exception {
        List<String> urls = scraperService.scrapeTeamUrls();
        for (String url : urls) {
            HtmlPage teamPage = scraperService.getWebClient().getPage(url);
            // Safe extraction with casting
            String name = safeExtractText(teamPage, "//h1", HtmlHeading1.class);
            String stadium = safeExtractText(teamPage, "//div[@class='stadium']", HtmlDivision.class);
            String manager = safeExtractText(teamPage, "//div[@class='manager']", HtmlDivision.class);
            String website = safeExtractHref(teamPage, "//a[contains(@href,'http')]");

            Team team = new Team(teams.size() + 1, name, 0, stadium, manager, website);
            teams.add(team);
        }
    }

    // Helper to safely extract text from HtmlElement
    private String safeExtractText(HtmlPage page, String xpath, Class<? extends HtmlElement> clazz) {
        try {
            HtmlElement raw = page.getFirstByXPath(xpath);
            if (raw == null) return "";
            return clazz.cast(raw).getTextContent();
        } catch (Exception e) {
            return "";
        }
    }

    // Helper to safely extract href from HtmlAnchor
    private String safeExtractHref(HtmlPage page, String xpath) {
        try {
            HtmlAnchor anchor = (HtmlAnchor) page.getFirstByXPath(xpath);
            return anchor != null ? anchor.getHrefAttribute() : "";
        } catch (Exception e) {
            return "";
        }
    }

    public Collection<Team> getAllTeams() {
        if (teams.isEmpty()) {
            throw new RuntimeException("No teams found");
        }
        return teams;
    }

    public Collection<Integer> getAllTeamIds() {
        List<Integer> teamIds = new ArrayList<>();
        for (Team team : teams) {
            teamIds.add(team.getId());
        }
        if (teamIds.isEmpty()) {
            throw new RuntimeException("No team IDs found");
        }
        return teamIds;
    }

    public Collection<String> getAllTeamNames() {
        List<String> teamNames = new ArrayList<>();
        for (Team team : teams) {
            teamNames.add(team.getName());
        }
        if (teamNames.isEmpty()) {
            throw new RuntimeException("No team names found");
        }
        return teamNames;
    }

    public Collection<Integer> getAllTeamLeagueRankings() {
        List<Integer> leagueRankings = new ArrayList<>();
        for (Team team : teams) {
            leagueRankings.add(team.getLeagueRanking());
        }
        if (leagueRankings.isEmpty()) {
            throw new RuntimeException("No league rankings found");
        }
        return leagueRankings;
    }

    public Collection<String> getAllTeamHomePitches() {
        List<String> homePitches = new ArrayList<>();
        for (Team team : teams) {
            homePitches.add(team.getHomePitch());
        }
        if (homePitches.isEmpty()) {
            throw new RuntimeException("No home pitches found");
        }
        return homePitches;
    }

    public Collection<String> getAllTeamManagers() {
        List<String> managers = new ArrayList<>();
        for (Team team : teams) {
            managers.add(team.getManager());
        }
        if (managers.isEmpty()) {
            throw new RuntimeException("No managers found");
        }
        return managers;
    }

    public Optional<Team> getTeamByID(Integer id) {
        if (id == null) {
            throw new RuntimeException("Team ID cannot be null");
        } else if (id < 1) {
            throw new RuntimeException("Team ID must be greater than 0. Team ID provided: " + id);
        } else if (id > teams.size()) {
            throw new RuntimeException("Team with ID " + id + " not found");
        }
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
        if (name == null) {
            throw new RuntimeException("Team name cannot be null");
        } else if (name.isEmpty()) {
            throw new RuntimeException("Team name cannot be empty. Team name provided: " + name);
        } else if (name.length() < 3) {
            throw new RuntimeException("Team name must be at least 3 characters long. Team name provided: " + name);
        } else if (name.length() > 50) {
            throw new RuntimeException("Team name must be less than 50 characters long. Team name provided: " + name);
        }
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
        if (leagueRanking < 1) {
            throw new RuntimeException("League ranking value must be greater than 0. League ranking value provided: " + leagueRanking);
        } else if (leagueRanking > teams.size()) {
            throw new RuntimeException("Team with league ranking " + leagueRanking + " not found");
        }
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
        if (homePitch == null) {
            throw new RuntimeException("Home pitch cannot be null");
        } else if (homePitch.isEmpty()) {
            throw new RuntimeException("Home pitch cannot be empty. Home pitch provided: " + homePitch);
        } else if (homePitch.length() < 3) {
            throw new RuntimeException("Home pitch must be at least 3 characters long. Home pitch provided: " + homePitch);
        } else if (homePitch.length() > 50) {
            throw new RuntimeException("Home pitch must be less than 50 characters long. Home pitch provided: " + homePitch);
        }
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
        if (manager == null) {
            throw new RuntimeException("Manager name cannot be null");
        } else if (manager.isEmpty()) {
            throw new RuntimeException("Manager name cannot be empty. Manager name provided: " + manager);
        } else if (manager.length() < 3) {
            throw new RuntimeException("Manager name must be at least 3 characters long. Manager name provided: " + manager);
        } else if (manager.length() > 50) {
            throw new RuntimeException("Manager name must be less than 50 characters long. Manager name provided: " + manager);
        }
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
