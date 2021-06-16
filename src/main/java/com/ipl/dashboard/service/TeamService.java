package com.ipl.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipl.dashboard.entity.Match;
import com.ipl.dashboard.entity.Team;
import com.ipl.dashboard.model.MatchModel;
import com.ipl.dashboard.model.TeamModel;
import com.ipl.dashboard.repository.TeamRepository;
import com.ipl.dashboard.util.MatchMapper;
import com.ipl.dashboard.util.TeamMapper;

@Service
public class TeamService {
	private @Autowired TeamRepository teamRepository;
	
	private @Autowired MatchService matchService;
	
	public TeamModel getTeam(final String teamName) {
		Team team = teamRepository.findByTeamName(teamName);
		List<Match> matches = matchService.getByTeam(teamName);
		TeamModel teamModel = TeamMapper.convertToModel(team);
		List<MatchModel> matchesModel = MatchMapper.convertToModel(matches);
		teamModel.setMatches(matchesModel);
		return teamModel;
	}
}
