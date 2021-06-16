package com.ipl.dashboard.cotroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipl.dashboard.model.TeamModel;
import com.ipl.dashboard.service.TeamService;

@RestController
@RequestMapping(value ="/api/v1/team")
public class TeamController {
	
	private @Autowired TeamService teamService;
	
	@GetMapping(value ="/{teamName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TeamModel> getTeam(@PathVariable final String teamName, 
			@RequestParam(name = "page", required = false) final Integer page,
			@RequestParam(name = "pageSize", required = false) final Integer pageSize) {
		TeamModel team = teamService.getTeam(teamName);
		return new ResponseEntity<>(team, HttpStatus.OK);
	}
}
