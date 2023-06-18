package in.projects.ipldashboard.repository;

import in.projects.ipldashboard.Model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);



}
