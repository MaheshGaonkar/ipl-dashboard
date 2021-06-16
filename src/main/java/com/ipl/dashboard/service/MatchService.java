package com.ipl.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipl.dashboard.entity.Match;
import com.ipl.dashboard.repository.MatchRepository;

@Service
public class MatchService {
	private @Autowired MatchRepository matchRepository;
	
	public List<Match> getByTeam(final String teamName) {
		return matchRepository.findLatestMatchesByTeam(teamName, 0, 4);
	}
}
