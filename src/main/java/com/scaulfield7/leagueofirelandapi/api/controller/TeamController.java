package com.scaulfield7.leagueofirelandapi.api.controller;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.constants.LeagueOfIrelandApiConstants;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public Iterable<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/team")
    public Team getTeam(@RequestParam String filter) {
        if (filter.matches("\\d+")) {
            Optional<Team> optionalTeam = teamService.getTeamByID(Integer.parseInt(filter));
            return optionalTeam.orElseThrow(() -> new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                    LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.ID +
                    LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
        } else {
            Optional<Team> optionalTeam = teamService.getTeamByName(filter);
            if (optionalTeam.isEmpty()) {
                optionalTeam = teamService.getTeamByName(filter.toLowerCase());
                return optionalTeam.or(() -> teamService.getTeamByManager(filter.toLowerCase())).orElseThrow(() ->
                        new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.TEAM +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.NAME + "/" +
                                LeagueOfIrelandApiConstants.MANAGER + LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            }
        }
        return teamService.getTeamByName(filter)
                .orElseThrow(() -> new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                        LeagueOfIrelandApiConstants.VALUE + LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
    }
}
