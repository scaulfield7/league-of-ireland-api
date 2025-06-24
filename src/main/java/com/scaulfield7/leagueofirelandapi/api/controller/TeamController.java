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

    @GetMapping("/team")
    public Team getTeam(@RequestParam Integer id) {
        Optional<Team> optionalTeam = teamService.getTeamByID(id);
        return optionalTeam.orElseThrow(() -> new RuntimeException("Team with ID " + id + " not found"));
    }
}
