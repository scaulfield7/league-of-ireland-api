package com.scaulfield7.leagueofirelandapi.api.controller;

import com.scaulfield7.leagueofirelandapi.api.model.Team;
import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TeamController {
    private TeamService teamService;

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
            return optionalTeam.orElseThrow(() -> new RuntimeException("Team with ID " + filter + " not found"));
        } else {
            Optional<Team> optionalTeam = teamService.getTeamByName(filter);
            return optionalTeam.orElseThrow(() -> new RuntimeException("Team named " + filter + " not found"));
        }
    }
}
