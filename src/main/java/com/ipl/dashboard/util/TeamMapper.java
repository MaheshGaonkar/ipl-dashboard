package com.ipl.dashboard.util;

import com.ipl.dashboard.entity.Team;
import com.ipl.dashboard.model.TeamModel;

public final class TeamMapper {
	private TeamMapper() {}
	
	public static TeamModel convertToModel(Team team) {
		return TeamModel.builder()
		.id(team.getId()).teamName(team.getTeamName())
		.totalMatches(team.getTotalMatches()).totalWins(team.getTotalWins())
		.build();
	}
}