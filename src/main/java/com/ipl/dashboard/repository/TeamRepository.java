package com.ipl.dashboard.repository;

import org.springframework.data.repository.CrudRepository;

import com.ipl.dashboard.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long>{
	Team findByTeamName(final String teamName);
}
