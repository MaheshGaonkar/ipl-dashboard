package com.ipl.dashboard.util;

import java.util.List;
import java.util.stream.Collectors;

import com.ipl.dashboard.entity.Match;
import com.ipl.dashboard.model.MatchModel;

public final class MatchMapper {
	private MatchMapper() {}
	
	public static List<MatchModel> convertToModel(List<Match> matches) {
		return matches.stream().map(match ->
		MatchModel.builder()
		.city(match.getCity()).date(match.getDate()).id(match.getId()).matchWinner(match.getMatchWinner())
		.playerOfMatch(match.getPlayerOfMatch()).result(match.getResult()).resultMargin(match.getResultMargin())
		.team1(match.getTeam1()).team2(match.getTeam2()).tossDecision(match.getTossDecision()).tossWinner(match.getTossWinner())
		.umpire1(match.getUmpire1()).umpire2(match.getUmpire2()).venue(match.getVenue())
		.build()).collect(Collectors.toList());
	}
}
