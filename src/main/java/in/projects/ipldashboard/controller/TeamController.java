package in.projects.ipldashboard.controller;

import in.projects.ipldashboard.Model.Match;
import in.projects.ipldashboard.Model.Team;
import in.projects.ipldashboard.repository.MatchRepository;
import in.projects.ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;


    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/teams/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        System.out.println("--------------------heloooooooooooooooooooo ----------------" +  teamName);
        Team team = this.teamRepository.findByTeamName(teamName);
        System.out.printf("----------TEAM:"+ team.getTeamName() + " ----" +team);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));

        return team;
    }

    @GetMapping("/teams/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
                teamName,startDate, endDate);

    }

    @GetMapping("/team")
    public Iterable<Team> getTeam(){
        return this.teamRepository.findAll();
    }
}
