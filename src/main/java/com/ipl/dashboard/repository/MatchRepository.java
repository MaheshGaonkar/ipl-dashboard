package com.ipl.dashboard.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ipl.dashboard.entity.Match;

public interface MatchRepository extends CrudRepository<Match, Long>{
	List<Match> getByTeam1OrTeam2OrderByDateDesc(final String teamName1, final String teamName2, Pageable pageable);
	
	default List<Match> findLatestMatchesByTeam(final String teamName, final int offset, final int count) {
		Pageable pageable = PageRequest.of(offset, count);
		return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, pageable);
	}
}