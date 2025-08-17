package com.scaulfield7.leagueofirelandapi.api.controller;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
<<<<<<< Updated upstream
import com.scaulfield7.leagueofirelandapi.constants.LeagueOfIrelandApiConstants;
=======
>>>>>>> Stashed changes
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< Updated upstream
import java.util.Collection;
import java.util.Optional;

@RestController
public class TeamController {
    private final TeamService teamService;
=======
@RestController
public class TeamController {
    private TeamService teamService;
>>>>>>> Stashed changes

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

<<<<<<< Updated upstream
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
            // TODO: Resolve issue with getting team by String values other than name always returning default exception message from last return statement
            Optional<Team> optionalTeam = Optional.empty();
            if (this.teamService.getAllTeamNames().contains(filter.toLowerCase())) {
                return optionalTeam.orElseThrow(() ->
                        new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.TEAM +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.NAME +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            } else if (this.teamService.getAllTeamManagers().contains(filter.toLowerCase())) {
                optionalTeam = teamService.getTeamByManager(filter.toLowerCase());
                return optionalTeam.orElseThrow(() ->
                        new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.TEAM +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.MANAGER +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            } else if (this.teamService.getAllTeamHomePitches().contains(filter.toLowerCase())) {
                optionalTeam = teamService.getTeamByHomePitch(filter.toLowerCase());
                return optionalTeam.orElseThrow(() ->
                        new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.TEAM +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.HOME_PITCH +
                                LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
            }
        }
        return teamService.getTeamByName(filter)
                .orElseThrow(() -> new RuntimeException(LeagueOfIrelandApiConstants.NO_TEAM_FOUND_WITH +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + LeagueOfIrelandApiConstants.VALUE +
                        LeagueOfIrelandApiConstants.EMPTY_SPACE + filter));
=======
    @GetMapping
    public Team getTeam(@RequestParam Integer id) {
        // Create dummy team for demonstration purposes
        return teamService.getTeam(id);
>>>>>>> Stashed changes
    }
}
