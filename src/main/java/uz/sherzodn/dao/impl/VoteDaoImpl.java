package uz.sherzodn.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import uz.sherzodn.dao.VoteDao;
import uz.sherzodn.model.Vote;
import uz.sherzodn.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@Repository("voteDao")
public class VoteDaoImpl extends GenericDaoImpl<Vote, Long> implements VoteDao {

    /**
     * Constructor to create a Generics-based version using Vote as the entity
     */
    public VoteDaoImpl() {
        super(Vote.class);
    }

    @Override
    public Vote getVoteByParams(Long userId, Date date) {
        Query query = getSession().createQuery("FROM Vote v where v.user.id=:userId and v.votedDate>=:beginOfTheDate order by v.votedDate desc")
                .setParameter("userId", userId)
                .setParameter("beginOfTheDate", DateUtils.getBeginOfTheDate(date));
        List votes = query.list();
        if (!votes.isEmpty()) {
            return (Vote) votes.get(0);
        }
        return null;
    }

    @Override
    public List<Vote> getVotesToday() {
        Query query = getSession().createQuery("FROM Vote v where v.votedDate>=:beginOfTheDate order by v.votedDate desc")
                .setParameter("beginOfTheDate", DateUtils.getBeginOfTheDate(new Date()));
        List votes = query.list();
        return votes;
    }


}
