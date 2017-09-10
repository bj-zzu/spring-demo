package cn.zzuzl.service;

import cn.zzuzl.common.util.ResultUtil;
import cn.zzuzl.dao.UserDao;
import cn.zzuzl.domain.User;
import cn.zzuzl.domain.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/10.
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private UserDao userDao;

    public Result addUser(User user) {
        Result result = null;
        try {
            userDao.addUser(user);
            result = ResultUtil.successResult();
        } catch (Exception e) {
            logger.error("addUser,exception-{}", user, e);
            result = ResultUtil.exceptionResult(e);
        }
        return result;
    }
}
