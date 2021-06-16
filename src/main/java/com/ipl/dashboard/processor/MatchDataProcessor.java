package com.ipl.dashboard.processor;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.ipl.dashboard.entity.Match;
import com.ipl.dashboard.model.MatchInput;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

	private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	@Override
	public Match process(final MatchInput matchInput) throws Exception {

		// Set Team 1 and 2 depending on the innings order
		String firstInningsTeam, secondInningsTeam;

		if ("bat".equalsIgnoreCase(matchInput.getToss_decision())) {
			firstInningsTeam = matchInput.getToss_winner();
			secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
					: matchInput.getTeam1();
		} else {
			secondInningsTeam = matchInput.getToss_winner();
			firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1()) ? matchInput.getTeam2()
					: matchInput.getTeam1();
		}

		Match match = Match.builder().id(Long.parseLong(matchInput.getId())).city(matchInput.getCity())
				.date(LocalDate.parse(matchInput.getDate())).playerOfMatch(matchInput.getPlayer_of_match())
				.venue(matchInput.getVenue()).team1(firstInningsTeam).team2(secondInningsTeam)
				.tossWinner(matchInput.getToss_winner()).tossDecision(matchInput.getToss_decision())
				.matchWinner(matchInput.getWinner())
				.result(matchInput.getResult()).resultMargin(matchInput.getResult_margin())
				.umpire1(matchInput.getUmpire1()).umpire2(matchInput.getUmpire2())
				.build();
		
		return match;
	}
}