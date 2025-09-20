package com.scaulfield7.leagueofirelandapi.service;

import com.gargoylesoftware.htmlunit.html.*;
import com.scaulfield7.leagueofirelandapi.api.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final ScraperService scraperService;
    private final List<Team> teams;

    public TeamService(ScraperService scraperService, PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer) throws Exception {
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
            if (team.getName().toLowerCase().equals(name.toLowerCase())) {
                optionalTeam = Optional.of(team);
                return optionalTeam;
            }
        }
        return optionalTeam;
    }

    public Iterable<Team> getAllTeams() {
        if (teams.isEmpty()) {
            throw new RuntimeException("No teams found");
        }
        return teams;
    }
}
