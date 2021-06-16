package com.ipl.dashboard.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TeamModel {
	private long id;
	private String teamName;
	private long totalMatches;
	private long totalWins;
	private List<MatchModel> matches;
}
