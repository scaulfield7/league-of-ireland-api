package com.scaulfield7.leagueofirelandapi.api.controller;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.constants.LeagueOfIrelandApiConstants;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public Collection<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/team")
    public Team getTeam(@RequestParam String filter) {
        if (filter.matches("\\d+")) {
            // TODO: Refactor this to account for IDs and league rankings having the same valid values
            if (this.teamService.getAllTeamIds().contains(Integer.parseInt(filter))) {
                Optional<Team> optionalTeam = teamService.getTeamByID(Integer.parseInt(filter));
                return optionalTeam.orElseThrow(() -> new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.ID +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            } else if (this.teamService.getAllTeamLeagueRankings().contains(Integer.parseInt(filter))) {
                Optional<Team> optionalTeam = teamService.getTeamByLeagueRanking(Integer.parseInt(filter));
                return optionalTeam.orElseThrow(() -> new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.LEAGUE_RANKING +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            }
        } else {
            Optional<Team> optionalTeam = teamService.getTeamByName(filter);
            if (optionalTeam.isEmpty()) {
                optionalTeam = teamService.getTeamByName(filter.toLowerCase());
                return optionalTeam
                        .or(() -> teamService.getTeamByManager(filter.toLowerCase()))
                        .or(() -> teamService.getTeamByHomePitch(filter.toLowerCase()))
                        .orElseThrow(() ->
                        new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.TEAM +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.NAME + "/" +
                                LeagueOfIrelandApiConstants.MANAGER + LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            }
        }
        return teamService.getTeamByName(filter)
                .orElseThrow(() -> new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.VALUE +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
    }
}
