package com.scaulfield7.leagueofirelandapi.api.controller;

import com.scaulfield7.leagueofirelandapi.service.TeamService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
}
