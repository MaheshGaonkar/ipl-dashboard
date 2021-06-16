package com.ipl.dashboard.listener;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ipl.dashboard.model.Team;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final EntityManager entityManager;

  @Autowired
  public JobCompletionNotificationListener(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("!!! JOB FINISHED! Time to verify the results");
      
      Map<String, Team> teamData = new HashMap<>();
      
      entityManager.createQuery("Select m.team1, count(*) from Match m group by m.team1", Object[].class)
    		  .getResultList()
    		  .stream()
    		  .map(e -> Team.builder().teamName((String)e[0]).totalMatches((long)e[1]).build())
    		  .forEach(team -> teamData.put(team.getTeamName(), team));
      
      entityManager.createQuery("Select m.team2, count(*) from Match m group by m.team2", Object[].class)
	  .getResultList()
	  .stream()
	  .filter(e -> Objects.nonNull(teamData.get((String)e[0])))
	  .forEach(e -> {
		  Team team = teamData.get((String)e[0]);
		  team.setTotalMatches(team.getTotalMatches() + (long)e[1]);
	  });
      
      entityManager.createQuery("Select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
	  .getResultList()
	  .stream()
	  .filter(e -> Objects.nonNull(teamData.get((String)e[0])))
	  .forEach(e -> {
		  Team team = teamData.get((String)e[0]);
		  team.setTotalWins(team.getTotalWins() + (long)e[1]);
	  });
      
      teamData.values().forEach(team -> entityManager.persist(team));
      teamData.values().forEach(System.out::println);
    }
  }
}