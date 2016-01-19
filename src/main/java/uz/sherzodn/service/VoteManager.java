package uz.sherzodn.service;

import uz.sherzodn.model.Vote;

import java.util.Date;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
public interface VoteManager extends GenericManager<Vote, Long> {

    Vote getVoteByParams(Long userId, Date date);

    List<Vote> getVotesToday();
}
