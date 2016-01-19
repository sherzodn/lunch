package uz.sherzodn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.sherzodn.dao.UserDao;
import uz.sherzodn.dao.VoteDao;
import uz.sherzodn.model.User;
import uz.sherzodn.model.Vote;
import uz.sherzodn.service.UserManager;
import uz.sherzodn.service.VoteManager;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
@Service("voteManager")
public class VoteManagerImpl extends GenericManagerImpl<Vote, Long> implements VoteManager {

    private VoteDao voteDao;

    @Autowired
    public void setVoteDao(VoteDao voteDao) {
        this.dao = voteDao;
        this.voteDao = voteDao;
    }

    @Override
    @Transactional
    public Vote getVoteByParams(Long userId, Date date) {
        return voteDao.getVoteByParams(userId, date);
    }

    @Override
    @Transactional
    public List<Vote> getVotesToday() {
        return voteDao.getVotesToday();
    }
}
