package uz.sherzodn.dao;

import uz.sherzodn.model.Vote;

import java.util.Date;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
public interface VoteDao extends GenericDao<Vote, Long> {

    Vote getVoteByParams(Long userId, Date date);

    List<Vote> getVotesToday();
}
