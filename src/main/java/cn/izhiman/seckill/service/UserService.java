package cn.izhiman.seckill.service;

import cn.izhiman.seckill.dao.UserDao;
import cn.izhiman.seckill.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhiman
 * @date 2019/9/22
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Transactional
    public void insert() {
        userDao.insert(2,"hh");
        userDao.insert(3,"hh");
    }
}
