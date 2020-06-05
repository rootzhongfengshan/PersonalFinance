package net.vv2.system.service.impl;

import net.vv2.system.domain.User;
import net.vv2.system.mapper.UserMapper;
import net.vv2.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongfs zhongfengshan@yahoo.com
 * @create 2017-06-08 13:11
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updUserPassword(User user) {
        return userMapper.updUserPassword(user);
    }

    @Override
    public int updUser(User user) {
        return userMapper.updUser(user);
    }

    @Override
    public int delUser(Integer id) {
        return userMapper.delUser(id);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public User selectUserByNameAndPassword(String name, String password) {
        return userMapper.selectUserByNameAndPassword(name,password);
    }
}
