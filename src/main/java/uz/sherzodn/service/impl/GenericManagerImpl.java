package uz.sherzodn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import uz.sherzodn.dao.GenericDao;
import uz.sherzodn.service.GenericManager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sherzod Nurjonov
 */
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK> {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected GenericDao<T, PK> dao;

    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public T get(PK id) {
        return dao.get(id);
    }

    @Override
    @Transactional
    public T save(T object) {
        return dao.save(object);
    }

    @Override
    @Transactional
    public void remove(PK id) {
        dao.remove(id);
    }
}
