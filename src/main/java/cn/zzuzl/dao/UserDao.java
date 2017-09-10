package cn.zzuzl.dao;

import cn.zzuzl.domain.User;

/**
 * Created by Administrator on 2017/9/10.
 */
public interface UserDao {
    int addUser(User user);

    User getById(Long id);

    User searchByUsername(String username);

}
